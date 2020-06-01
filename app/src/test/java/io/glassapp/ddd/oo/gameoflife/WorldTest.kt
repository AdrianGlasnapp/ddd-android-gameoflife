package io.glassapp.ddd.oo.gameoflife

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class WorldTest {

    @Test
    fun `newWorld creates new World with random live Cells`() {
        val rows = 9
        val columns = 9

        val world = World.newWorld(rows, columns)

        assertEquals(rows, world.rows)
        assertEquals(columns, world.columns)
    }

    @Test
    fun `nextGeneration is empty when Cells have no neighbors`() {
        val rows = 9
        val columns = 9
        val cells = setOf(Cell(Position(0, 1)), Cell(Position(3, 3)))
        val world = World.newWorld(UUID.randomUUID(), rows, columns, cells)

        world.nextGeneration()

        assertEquals(0, world.cells.size)
    }
}