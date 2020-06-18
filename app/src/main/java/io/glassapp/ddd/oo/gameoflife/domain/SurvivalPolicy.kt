package io.glassapp.ddd.oo.gameoflife.domain

interface SurvivalPolicy {

    fun survives(liveNeighbors: Int): Boolean
}