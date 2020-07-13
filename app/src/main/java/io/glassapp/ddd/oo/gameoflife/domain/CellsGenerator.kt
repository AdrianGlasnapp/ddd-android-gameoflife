package io.glassapp.ddd.oo.gameoflife.domain

class CellsGenerator {

    companion object {

        fun generate(rows: Int, columns: Int): Set<Position> {
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
    }
}