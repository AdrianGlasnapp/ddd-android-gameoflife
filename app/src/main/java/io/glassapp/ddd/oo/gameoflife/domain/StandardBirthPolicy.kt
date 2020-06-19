package io.glassapp.ddd.oo.gameoflife.domain

class StandardBirthPolicy : BirthPolicy {
    private val liveNeighborsForBirth: Set<Int> = setOf(3)

    override fun born(liveNeighbors: Int): Boolean {
        return liveNeighborsForBirth.contains(liveNeighbors)
    }
}