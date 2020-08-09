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