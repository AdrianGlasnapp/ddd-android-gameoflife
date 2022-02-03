package io.glassapp.ddd.oo.gameoflife.domain

/**
 * Policy
 */
class StandardNeighborsPolicy(private val rows: Int, private val columns: Int) : NeighborsPolicy {

    override fun calculateNeighbors(position: Position): Set<Position> {
        return allNeighbors(position)
            .filter { isInBounds(it) }
            .toSet()
    }

    private fun allNeighbors(position: Position): List<Position> {
        return listOf(
            Position(
                position.row - 1,
                position.column
            ),
            Position(
                position.row - 1,
                position.column - 1
            ),
            Position(
                position.row,
                position.column - 1
            ),
            Position(
                position.row + 1,
                position.column - 1
            ),
            Position(
                position.row + 1,
                position.column
            ),
            Position(
                position.row + 1,
                position.column + 1
            ),
            Position(
                position.row,
                position.column + 1
            ),
            Position(
                position.row - 1,
                position.column + 1
            )
        )
    }

    private fun isInBounds(position: Position): Boolean {
        return position.row >= 0 && position.column >= 0
                && position.row <= rows && position.column < columns
    }
}