package io.glassapp.ddd.oo.gameoflife.domain

import io.glassapp.ddd.oo.gameoflife.domain.BirthPolicy

class StandardBirthPolicy : BirthPolicy {
    private val liveNeighborsForBirth: Set<Int> = setOf(3)

    override fun born(liveNeighbors: Int): Boolean {
        return liveNeighborsForBirth.contains(liveNeighbors)
    }
}