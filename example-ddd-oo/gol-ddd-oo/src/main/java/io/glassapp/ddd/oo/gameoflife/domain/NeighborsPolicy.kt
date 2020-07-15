package io.glassapp.ddd.oo.gameoflife.domain

interface NeighborsPolicy {

    fun calculateNeighbors(position: Position): Set<Position>
}