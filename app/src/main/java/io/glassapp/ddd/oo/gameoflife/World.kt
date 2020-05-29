package io.glassapp.ddd.oo.gameoflife

import java.util.*

class World(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    val cells: Set<Cell>
) {

    fun nextGeneration() {

    }

    companion object {

        fun newWorld(rows: Int, columns: Int): World {
            val cells = mutableSetOf<Cell>()
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    if ((Math.random() < 0.3)) {
                        cells.add(Cell(Position(i, j)))
                    }
                }
            }
            return World(
                UUID.randomUUID(),
                rows,
                columns,
                cells.toSet()
            )
        }

        fun newWorld(id: UUID, rows: Int, columns: Int, cells: Set<Cell>): World {
            return World(
                id,
                rows,
                columns,
                cells
            )
        }


    }
}