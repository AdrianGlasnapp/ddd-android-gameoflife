package io.glassapp.ddd.oo.gameoflife.domain

import java.util.*

/**
 * Aggregate
 */
class World internal constructor(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    var cells: Set<Cell>
) {
    private val survivalPolicy: SurvivalPolicy = StandardSurvivalPolicy()
    private val birthPolicy: BirthPolicy = StandardBirthPolicy()
    private val neighborsPolicy: NeighborsPolicy = StandardNeighborsPolicy(rows, columns)

    fun data(): WorldData {
        return WorldData(
            id,
            rows,
            columns,
            cells.map { it.position }.toSet()
        )
    }

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
}