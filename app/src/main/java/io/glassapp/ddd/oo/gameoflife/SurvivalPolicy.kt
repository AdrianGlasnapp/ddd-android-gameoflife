package io.glassapp.ddd.oo.gameoflife

interface SurvivalPolicy {

    fun survives(liveNeighbors: Int): Boolean
}