package io.glassapp.ddd.oo.gameoflife.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.glassapp.ddd.oo.gameoflife.GolContext
import io.glassapp.ddd.oo.gameoflife.domain.WorldData
import io.glassapp.gameoflife.R
import kotlinx.android.synthetic.main.activity_main.*

class GolActivity : AppCompatActivity() {
    private val gofContext = GolContext()
    private lateinit var worldData: WorldData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rows = 24
        val columns = 16
        val iterations = 100
        val sleep = 500L

        val pixelGrid = PixelGridView(this, columns, rows)
        boardContainer.addView(pixelGrid)

        worldData = gofContext.gameService.createNewWorld(rows, columns)

        Thread(
            Runnable {
                for (i in 0 until iterations) {
                    worldData = gofContext.gameService.generateNextIteration(worldData.id)

                    runOnUiThread {
                        pixelGrid.render(worldData)
                    }
                    Thread.sleep(sleep)
                }
            }
        ).start()
    }
}