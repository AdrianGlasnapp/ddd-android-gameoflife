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
    fun `should create random world`() {
        val gameService = gofContext.gameService
        val worldSeed = WorldSeed(5, 8)

        val newWorldData = gameService.createNewWorld(worldSeed)
        val worldData = gameService.findWorld(newWorldData.id)

        assertEquals(worldSeed.rows, worldData.rows)
        assertEquals(worldSeed.columns, worldData.columns)
    }

    @Test
    fun `should create world with predefined seed`() {
        val gameService = gofContext.gameService
        val position = Position(1, 1)
        val livingCells = setOf(position)
        val rows = 5
        val columns = 8

        val newWorldData = gameService.createNewWorld(rows, columns, livingCells)
        val worldData = gameService.findWorld(newWorldData.id)

        assertEquals(newWorldData.id, worldData.id)
        assertEquals(columns, worldData.columns)
        assertEquals(rows, worldData.rows)
        assertEquals(livingCells, worldData.livingCellPositions)
    }

    @Test
    fun `should return existing world by id`() {
        val gameService = gofContext.gameService
        val position = Position(1, 1)
        val livingCells = setOf(position)
        val rows = 5
        val columns = 8

        val newWorldData = gameService.createNewWorld(rows, columns, livingCells)
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
    fun `should generate next iteration for given world`() {
        val gameService = gofContext.gameService
        val position = Position(1, 1)
        val livingCells = setOf(position)
        val rows = 5
        val columns = 8
        val newWorldData = gameService.createNewWorld(rows, columns, livingCells)
        val worldData = gameService.findWorld(newWorldData.id)

        val nextWorldData = gameService.generateNextIteration(worldData.id)

        assertEquals(newWorldData.id, worldData.id)
        assertEquals(nextWorldData.columns, worldData.columns)
        assertEquals(nextWorldData.rows, worldData.rows)
//        assertEquals(livingCells, worldData.livingCellPositions)
    }

    private fun createRowAndColumnAndSeed(row: Int, column: Int, livingCells: Set<Position>): Triple<Int, Int, Set<Position>> {
        return Triple(row, column, livingCells)
    }

    private fun createRowAndColumnPair(row: Int, column: Int): Pair<Int, Int> {
        return Pair(row, column)
    }

}