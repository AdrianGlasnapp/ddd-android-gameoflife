package io.glassapp.gameoflife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pixelGrid = PixelGridView(this, 16, 24)

        val grid = Grid(24, 16)
        val generation = grid.nextGeneration()

        boardContainer.addView(pixelGrid)
        pixelGrid.render(generation)
    }

}
