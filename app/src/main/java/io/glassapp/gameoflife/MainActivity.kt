package io.glassapp.gameoflife

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var grid: Array<IntArray>
    private var rows = 0
    private var columns = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pixelGrid = PixelGridView(this, 16, 24)
        boardContainer.addView(pixelGrid)

        generateRandomGrid(24, 16)
        val iterations = 100
        val sleep = 500L

        Thread(
            Runnable {
                for (i in 0 until iterations) {
                    grid = nextGeneration()

                    runOnUiThread {
                        pixelGrid.render(grid)
                    }
                    Thread.sleep(sleep)
                }
            }
        ).start()
    }

    fun generateRandomGrid(rows: Int, columns: Int) {
        grid = Array(rows) { IntArray(columns) }
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                grid[i][j] = Math.random().roundToInt()
            }
        }
        this.rows = rows
        this.columns = columns
    }

    fun nextGeneration(): Array<IntArray> {
        val futureGeneration = Array(rows) { IntArray(columns) }
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                futureGeneration[i][j] = getNextStateForCell(i, j)
            }
        }
        return futureGeneration
    }

    private fun getNextStateForCell(i: Int, j: Int): Int {
        val aliveNeighbors = calculateLivingNeighbors(i, j)
        return if (grid[i][j] == 1 && aliveNeighbors < 2) {
            0
        } else if (grid[i][j] == 1 && aliveNeighbors > 3) {
            0
        } else if (grid[i][j] == 0 && aliveNeighbors == 3) {
            1
        } else {
            grid[i][j]
        }
    }

    private fun calculateLivingNeighbors(i: Int, j: Int): Int {
        var liveCount = 0
        for (x in -1..1) {
            for (y in -1..1) {
                if (i + x < 0 || i + x > rows - 1 || y + j < 0 || y + j > columns - 1) {
                    continue
                }
                liveCount += grid[i + x][y + j]
            }
        }
        liveCount -= grid[i][j]
        return liveCount
    }
}