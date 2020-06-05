package io.glassapp.ddd.oo.gameoflife

interface NeighborsPolicy {

    fun calculateNeighbors(position: Position): Set<Position>
}