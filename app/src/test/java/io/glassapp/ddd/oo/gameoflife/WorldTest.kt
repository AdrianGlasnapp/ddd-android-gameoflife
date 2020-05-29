package io.glassapp.ddd.oo.gameoflife

import org.junit.Test

import org.junit.Assert.*

class WorldTest {

    @Test
    fun `newWorld creates new World with random live Cells`() {
        val rows = 24
        val columns = 16

        val world = World.newWorld(rows, columns)

        assertEquals(rows, world.rows)
        assertEquals(columns, world.columns)
    }
}