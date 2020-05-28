package io.glassapp.gameoflife

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PixelGridView @JvmOverloads constructor(
    context: Context?,
    private var numColumns: Int,
    private var numRows: Int
) : View(context, null) {
    private var cellWidth = 0
    private var cellHeight = 0
    private val blackPaint = Paint()
    private var cellChecked: Array<IntArray> = Array(numRows) { IntArray(numColumns) }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
    }

    private fun calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return
        }
        cellWidth = width / numColumns
        cellHeight = height / numRows
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        if (numColumns == 0 || numRows == 0) {
            return
        }
        val width = width
        val height = height

        for (i in 0 until numRows - 1) {
            for (j in 0 until numColumns - 1) {
                if (cellChecked[i][j] == 1) {
                    canvas.drawRect(
                        i * cellWidth.toFloat(), j * cellHeight.toFloat(),
                        (i + 1) * cellWidth.toFloat(), (j + 1) * cellHeight.toFloat(),
                        blackPaint
                    )
                }
            }
        }
        for (i in 1 until numColumns) {
            canvas.drawLine(
                i * cellWidth.toFloat(),
                0f,
                i * cellWidth.toFloat(),
                height.toFloat(),
                blackPaint
            )
        }
        for (i in 1 until numRows) {
            canvas.drawLine(
                0f,
                i * cellHeight.toFloat(),
                width.toFloat(),
                i * cellHeight.toFloat(),
                blackPaint
            )
        }
    }


    fun render(generation: Array<IntArray>) {
        cellChecked = generation
        invalidate()
    }

    init {
        blackPaint.style = Paint.Style.FILL_AND_STROKE
    }
}