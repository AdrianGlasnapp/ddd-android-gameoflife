package io.glassapp.ddd.oo.gameoflife

interface BirthPolicy {
    fun born(liveNeighbors: Int): Boolean
}