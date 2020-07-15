package io.glassapp.ddd.oo.gameoflife.domain

interface BirthPolicy {
    fun born(liveNeighbors: Int): Boolean
}