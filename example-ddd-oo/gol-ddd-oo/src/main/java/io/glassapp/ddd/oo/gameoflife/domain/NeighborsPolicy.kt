package io.glassapp.ddd.oo.gameoflife.domain

/**
 * Policy
 */
interface NeighborsPolicy {

    fun calculateNeighbors(position: Position): Set<Position>
}