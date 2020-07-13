package io.glassapp.ddd.oo.gameoflife.application

import io.glassapp.ddd.oo.gameoflife.domain.*
import java.util.*

class GameService(private val worldRepository: WorldRepository) {


    fun createNewWorld(rows: Int, columns: Int): WorldData {
        val worldSeed = WorldSeed(rows, columns, CellsGenerator.generate(rows, columns))
        return createNewWorld(worldSeed)
    }

    fun createNewWorld(rows: Int, columns: Int, livingCellsPositions: Set<Position>): WorldData {
        val worldSeed = WorldSeed(rows, columns, livingCellsPositions)
        return createNewWorld(worldSeed)
    }

    fun createNewWorld(worldSeed: WorldSeed): WorldData {
        val livingCells = worldSeed.livingCells.map { Cell(it) }.toSet()
        val world = World(UUID.randomUUID(), worldSeed.rows, worldSeed.columns, livingCells)

        worldRepository.add(world)

        return world.data()
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