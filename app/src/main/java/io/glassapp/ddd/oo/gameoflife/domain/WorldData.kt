package io.glassapp.ddd.oo.gameoflife.domain

import java.util.*

class WorldData(
    val id: UUID,
    val rows: Int,
    val columns: Int,
    var livingCellPositions: List<Position>
) {
}