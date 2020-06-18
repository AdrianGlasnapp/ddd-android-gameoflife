package io.glassapp.ddd.oo.gameoflife.domain

class StandardSurvivalPolicy :
    SurvivalPolicy {
    private val liveNeighborsForSurvival: Set<Int> = setOf(2, 3)

    override fun survives(liveNeighbors: Int): Boolean {
        return liveNeighborsForSurvival.contains(liveNeighbors)
    }
}