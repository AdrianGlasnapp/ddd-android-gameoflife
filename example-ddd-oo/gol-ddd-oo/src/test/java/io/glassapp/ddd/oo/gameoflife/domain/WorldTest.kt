package io.glassapp.ddd.oo.gameoflife.domain

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class WorldTest {

    @Test
    fun `newWorld creates new World with random live Cells`() {
        val rows = 9
        val columns = 9

        val world = World(UUID.randomUUID(), rows, columns, setOf())

        assertEquals(rows, world.rows)
        assertEquals(columns, world.columns)
    }

    @Test
    fun `nextGeneration is empty when Cell have no neighbors`() {
        val rows = 9
        val columns = 9
        val cells = setOf(
            Cell(Position(0, 1)),
            Cell(Position(3, 3))
        )
        val world = World(UUID.randomUUID(), rows, columns, cells)

        world.nextGeneration()

        assertEquals(0, world.cells.size)
    }

    @Test
    fun `nextGeneration is empty when Cell have 1 neighbor`() {
        val rows = 9
        val columns = 9
        val cells = setOf(
            Cell(Position(0, 1)),
            Cell(Position(3, 3)),
            Cell(Position(1, 1))
        )
        val world = World(UUID.randomUUID(), rows, columns, cells)

        world.nextGeneration()

        assertEquals(0, world.cells.size)
    }

    @Test
    fun `nextGeneration Cell survives when have 2 neighbor`() {
        val rows = 9
        val columns = 9
        val cells = setOf(
            Cell(Position(0, 0)),
            Cell(Position(0, 1)),
            Cell(Position(0, 2))
        )
        val world = World(UUID.randomUUID(), rows, columns, cells)

        world.nextGeneration()

        assertEquals(3, world.cells.size)
    }
}