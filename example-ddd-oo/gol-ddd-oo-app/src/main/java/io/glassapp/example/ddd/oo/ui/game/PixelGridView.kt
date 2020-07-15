package io.glassapp.example.ddd.oo.ui.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import io.glassapp.ddd.oo.gameoflife.domain.WorldData

class PixelGridView constructor(
    context: Context?,
    private var numColumns: Int,
    private var numRows: Int
) : View(context, null) {
    private var cellWidth: Float = 0.0F
    private var cellHeight: Float = 0.0F
    private val blackPaint = Paint()
    private var worldData: WorldData? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
    }

    private fun calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return
        }
        cellWidth = (width / numColumns).toFloat()
        cellHeight = (height / numRows).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        if (numColumns == 0 || numRows == 0) {
            return
        }
        drawCells(canvas)
        drawColumnLines(canvas, height)
        drawRowLines(canvas, width)
    }

    private fun drawCells(canvas: Canvas) {
        worldData?.livingCellPositions
            ?.forEach { position ->
                canvas.drawRect(
                    position.column * cellWidth, position.row * cellHeight,
                    (position.column + 1) * cellWidth, (position.row + 1) * cellHeight,
                    blackPaint
                )
            }
    }

    private fun drawColumnLines(canvas: Canvas, height: Int) {
        for (i in 1 until numColumns) {
            canvas.drawLine(
                i * cellWidth,
                0f,
                i * cellWidth,
                height.toFloat(),
                blackPaint
            )
        }
    }

    private fun drawRowLines(canvas: Canvas, width: Int) {
        for (i in 1 until numRows) {
            canvas.drawLine(
                0f,
                i * cellHeight,
                width.toFloat(),
                i * cellHeight,
                blackPaint
            )
        }
    }


    fun render(worldData: WorldData) {
        this.worldData = worldData
        invalidate()
    }

    init {
        blackPaint.style = Paint.Style.FILL_AND_STROKE
    }
}