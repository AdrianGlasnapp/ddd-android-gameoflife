package io.glassapp.ddd.oo.gameoflife.domain

import java.util.*

/**
 * Domain Service
 */
class CellsGenerator {

    companion object {

        fun generate(rows: Int, columns: Int): Set<Position> {
            val positions = generatePositionSet(rows, columns)
            if (positions.isEmpty()) {
                return setOf(randomPosition(rows, columns))
            }
            return positions
        }

        private fun generatePositionSet(rows: Int, columns: Int): Set<Position> {
            val positions = mutableSetOf<Position>()
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    if (Math.random() < 0.3) {
                        positions.add(
                            Position(
                                i,
                                j
                            )
                        )
                    }
                }
            }
            return positions
        }

        private fun randomPosition(rows: Int, columns: Int): Position {
            val random = Random()
            val row = random.nextInt(rows)
            val column = random.nextInt(columns)
            return Position(row, column)
        }
    }
}