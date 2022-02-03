package io.glassapp.ddd.oo.gameoflife.domain

import java.util.*

/**
 * Value Object
 */
data class WorldData(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    var livingCellPositions: Set<Position>
)


val input = arrayOf(
    intArrayOf(1, 1, 0, 0, 0, 0, 1, 1, 0, 0),
    intArrayOf(1, 1, 0, 0, 1, 1, 0, 1, 1, 0),
    intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 0, 0),
    intArrayOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
    intArrayOf(1, 0, 0, 0, 1, 0, 0, 1, 0, 1),
    intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 0, 0),
    intArrayOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(1, 0, 1, 0, 0, 0, 0, 0, 1, 1),
    intArrayOf(1, 1, 0, 0, 1, 0, 0, 1, 1, 0)
)

val output = arrayOf(
    intArrayOf(1, 1, 0, 0, 0, 1, 1, 1, 1, 0),
    intArrayOf(0, 0, 0, 1, 1, 1, 0, 0, 1, 0),
    intArrayOf(1, 0, 0, 1, 0, 1, 0, 1, 0, 0),
    intArrayOf(1, 1, 0, 1, 0, 0, 0, 1, 0, 0),
    intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 0, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 0, 0, 0, 0, 0, 1, 1, 0),
    intArrayOf(1, 0, 1, 1, 0, 0, 0, 1, 1, 1),
    intArrayOf(1, 1, 0, 0, 0, 0, 0, 1, 1, 1)
)

fun test() {
    input[0][1]
}