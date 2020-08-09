package io.glassapp.ddd.oo.gameoflife.domain

/**
 * Policy
 */
interface SurvivalPolicy {

    fun survives(liveNeighbors: Int): Boolean
}