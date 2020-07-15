package io.glassapp.example.ddd.oo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.glassapp.ddd.oo.gameoflife.GolContext
import io.glassapp.ddd.oo.gameoflife.domain.WorldData
import io.glassapp.example.ddd.oo.R
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private val gofContext = GolContext()
    private lateinit var worldData: WorldData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // TODO: move to ViewModel
        val rows = 24
        val columns = 16
        val iterations = 100
        val sleep = 500L

        val pixelGrid = PixelGridView(requireContext(), columns, rows)
        boardContainer.addView(pixelGrid)

        worldData = gofContext.gameService.createNewWorld(rows, columns)

        Thread(
            Runnable {
                for (i in 0 until iterations) {
                    worldData = gofContext.gameService.generateNextIteration(worldData.id)

                    activity?.runOnUiThread {
                        pixelGrid.render(worldData)
                    }
                    Thread.sleep(sleep)
                }
            }
        ).start()
    }

}
