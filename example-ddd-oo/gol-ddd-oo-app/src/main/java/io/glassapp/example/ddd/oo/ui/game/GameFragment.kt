package io.glassapp.example.ddd.oo.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.glassapp.example.ddd.oo.R
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val rows = 24
        val columns = 16
        val pixelGrid = PixelGridView(requireContext(), columns, rows)
        boardContainer.addView(pixelGrid)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.worldData().observe(this.viewLifecycleOwner, Observer {
            pixelGrid.render(it)
        })
        viewModel.start(rows, columns)
    }

}