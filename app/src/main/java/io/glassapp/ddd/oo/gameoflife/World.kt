package io.glassapp.ddd.oo.gameoflife

import java.util.*

class World(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    var cells: Set<Cell>
) {
    private val survivalPolicy: SurvivalPolicy = StandardSurvivalPolicy()
    private val birthPolicy: BirthPolicy = StandardBirthPolicy()
    private val neighborsPolicy: NeighborsPolicy = StandardNeighborsPolicy()

    fun nextGeneration() {
        cells = (survivingCells() + bornCells()).toSet()
    }

    private fun survivingCells(): List<Cell> {
        return cells
            .filter { survivalPolicy.survives(countLiveNeighbors(it.position)) }
    }

    private fun bornCells(): List<Cell> {
        return deadNeighbors(cells)
            .filter { birthPolicy.born(countLiveNeighbors(it)) }
            .map { Cell(it) }
    }

    private fun deadNeighbors(cells: Set<Cell>): List<Position> {
        return cells
            .flatMap { deadNeighbors(it.position) }
            .distinct()
    }

    private fun deadNeighbors(position: Position): List<Position> {
        return neighborsPolicy.calculateNeighbors(position)
            .filterNot { isAlive(it) }
    }

    private fun liveNeighbors(position: Position): List<Position> {
        return neighborsPolicy.calculateNeighbors(position)
            .filter { isAlive(it) }
    }

    private fun isAlive(position: Position): Boolean {
        return cells.contains(Cell(position))
    }

    private fun countLiveNeighbors(position: Position): Int {
        return liveNeighbors(position).count()
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