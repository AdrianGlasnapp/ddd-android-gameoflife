package io.glassapp.ddd.oo.gameoflife.application

import io.glassapp.ddd.oo.gameoflife.domain.*
import java.util.*

/**
 * Application Service
 */
class GameService(private val worldRepository: WorldRepository) {


    fun createNewWorld(rows: Int, columns: Int): WorldData {
        return createNewWorld(rows, columns, CellsGenerator.generate(rows, columns))
    }

    fun createNewWorld(rows: Int, columns: Int, livingCellsPositions: Set<Position>): WorldData {
        val worldSeed = WorldSeed(rows, columns, livingCellsPositions)
        return createNewWorld(worldSeed)
    }

    fun createNewWorld(worldSeed: WorldSeed): WorldData {
        val livingCells = prepareLivingCells(worldSeed)
        val world = World(UUID.randomUUID(), worldSeed.rows, worldSeed.columns, livingCells)

        worldRepository.add(world)

        return world.data()
    }

    private fun prepareLivingCells(worldSeed: WorldSeed): Set<Cell> {
        val livingCellsPositions = if (worldSeed.livingCells.isEmpty()) {
            CellsGenerator.generate(worldSeed.rows, worldSeed.columns)
        } else {
            worldSeed.livingCells
        }
        return livingCellsPositions.map { Cell(it) }.toSet()
    }


    fun generateNextIteration(worldId: UUID): WorldData {
        val world = worldRepository.find(worldId) ?: throw WorldNotFoundException()

        world.nextGeneration()

        worldRepository.update(world)
        return world.data()
    }

    fun findWorld(worldId: UUID): WorldData {
        val world = worldRepository.find(worldId) ?: throw WorldNotFoundException()
        return world.data()
    }
}