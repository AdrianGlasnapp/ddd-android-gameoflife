package io.glassapp.ddd.oo.gameoflife

import java.util.*

class World(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    var cells: Set<Cell>
) {
    private val liveNeighborsForSurvival: Set<Int> = setOf(2, 3)
    private val liveNeighborsForBirth: Set<Int> = setOf(3)

    fun nextGeneration() {
        cells = (survivingCells() + bornCells()).toSet()
    }

    private fun survivingCells(): List<Cell> {
        return cells
            .filter { survives(it.position) }
    }

    private fun bornCells(): List<Cell> {
        return deadNeighbors(cells)
            .filter { born(it) }
            .map { Cell(it) }
    }

    private fun deadNeighbors(cells: Set<Cell>): List<Position> {
        return cells
            .flatMap { deadNeighbors(it.position) }
            .distinct()
    }

    private fun deadNeighbors(cell: Position): List<Position> {
        return neighbors(cell)
            .filterNot { isAlive(it) }
    }

    private fun liveNeighbors(cell: Position): List<Position> {
        return neighbors(cell)
            .filter { isAlive(it) }
    }

    private fun isAlive(position: Position): Boolean {
        return cells.contains(Cell(position))
    }

    private fun neighbors(position: Position): Set<Position> {
        val cell = Cell(position)
        return setOf(
            Position(cell.position.row - 1, cell.position.column),
            Position(cell.position.row - 1, cell.position.column - 1),
            Position(cell.position.row, cell.position.column - 1),
            Position(cell.position.row + 1, cell.position.column - 1),
            Position(cell.position.row + 1, cell.position.column),
            Position(cell.position.row + 1, cell.position.column + 1),
            Position(cell.position.row, cell.position.column + 1),
            Position(cell.position.row - 1, cell.position.column + 1)
        )
    }

    private fun survives(cell: Position): Boolean {
        return survives(countLiveNeighbors(cell))
    }

    private fun born(cell: Position): Boolean {
        return born(countLiveNeighbors(cell))
    }

    private fun survives(liveNeighbors: Int): Boolean {
        return liveNeighborsForSurvival.contains(liveNeighbors)
    }

    private fun born(liveNeighbors: Int): Boolean {
        return liveNeighborsForBirth.contains(liveNeighbors)
    }

    private fun countLiveNeighbors(cell: Position): Int {
        return liveNeighbors(cell).count()
    }

    companion object {

        fun newWorld(rows: Int, columns: Int): World {
            val cells = mutableSetOf<Cell>()
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    if (Math.random() < 0.3) {
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