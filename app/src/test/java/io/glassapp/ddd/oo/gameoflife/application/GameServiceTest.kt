package io.glassapp.ddd.oo.gameoflife.application

import io.glassapp.ddd.oo.gameoflife.GolContext
import io.glassapp.ddd.oo.gameoflife.domain.Position
import io.glassapp.ddd.oo.gameoflife.domain.WorldSeed
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import java.util.*

class GameServiceTest {
    private val gofContext = GolContext()

    @Test
    fun `should create random world from rows and columns`() {
        val gameService = gofContext.gameService
        val rows = 5
        val columns = 8

        val newWorldData = gameService.createNewWorld(rows, columns)
        val worldData = gameService.findWorld(newWorldData.id)

        assertEquals(rows, worldData.rows)
        assertEquals(columns, worldData.columns)
        assertNotEquals(setOf<Position>(), worldData.livingCellPositions)
    }

    @Test
    fun `should create random world from seed when no living cells position was provided`() {
        val gameService = gofContext.gameService
        val worldSeed = WorldSeed(5, 8)

        val newWorldData = gameService.createNewWorld(worldSeed)
        val worldData = gameService.findWorld(newWorldData.id)

        assertEquals(worldSeed.rows, worldData.rows)
        assertEquals(worldSeed.columns, worldData.columns)
        assertNotEquals(setOf<Position>(), worldData.livingCellPositions)
    }

    @Test
    fun `should create world from predefined seed`() {
        val gameService = gofContext.gameService
        val worldSeed = createSeed()

        val newWorldData = gameService.createNewWorld(worldSeed)
        val worldData = gameService.findWorld(newWorldData.id)

        assertEquals(newWorldData.id, worldData.id)
        assertEquals(worldSeed.columns, worldData.columns)
        assertEquals(worldSeed.rows, worldData.rows)
        assertEquals(worldSeed.livingCells, worldData.livingCellPositions)
    }

    @Test
    fun `should return existing world by id`() {
        val gameService = gofContext.gameService
        val worldSeed = createSeed()

        val newWorldData = gameService.createNewWorld(worldSeed)
        val worldData = gameService.findWorld(newWorldData.id)

        assertNotNull(worldData)
    }

    @Test(expected = Exception::class)
    fun `should return exception when world does not exist`() {
        val gameService = gofContext.gameService
        val id = UUID.randomUUID()

        gameService.findWorld(id)
    }

    @Test
    fun `should generate next empty iteration for world with only 1 cell`() {
        val gameService = gofContext.gameService
        val worldSeed = createSeed()
        val newWorldData = gameService.createNewWorld(worldSeed)
        val worldData = gameService.findWorld(newWorldData.id)

        val nextWorldData = gameService.generateNextIteration(worldData.id)

        assertEquals(newWorldData.id, nextWorldData.id)
        assertEquals(worldData.columns, nextWorldData.columns)
        assertEquals(worldData.rows, nextWorldData.rows)
        assertEquals(emptySet<Position>(), nextWorldData.livingCellPositions)
    }

    private fun createSeed(rows: Int = 5, columns: Int = 8): WorldSeed {
        val position = Position(1, 1)
        val livingCells = setOf(position)
        return WorldSeed(rows, columns, livingCells)
    }

}