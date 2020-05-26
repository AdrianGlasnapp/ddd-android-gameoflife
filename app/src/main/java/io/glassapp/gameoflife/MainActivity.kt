package io.glassapp.gameoflife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pixelGrid = PixelGridView(this)
        pixelGrid.setNumColumns ( 16)
        pixelGrid.setNumRows  (24)

        boardContainer.addView(pixelGrid)
    }
}
