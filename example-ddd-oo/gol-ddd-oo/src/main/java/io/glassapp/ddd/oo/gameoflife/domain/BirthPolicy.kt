package io.glassapp.ddd.oo.gameoflife.domain

/**
 * Policy
 */
interface BirthPolicy {
    fun born(liveNeighbors: Int): Boolean
}