package io.glassapp.ddd.oo.gameoflife

class StandardNeighborsPolicy : NeighborsPolicy {

    override fun calculateNeighbors(position: Position): Set<Position> {
        return setOf(
            Position(position.row - 1, position.column),
            Position(position.row - 1, position.column - 1),
            Position(position.row, position.column - 1),
            Position(position.row + 1, position.column - 1),
            Position(position.row + 1, position.column),
            Position(position.row + 1, position.column + 1),
            Position(position.row, position.column + 1),
            Position(position.row - 1, position.column + 1)
        )
    }
}