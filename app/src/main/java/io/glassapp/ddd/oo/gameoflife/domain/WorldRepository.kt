package io.glassapp.ddd.oo.gameoflife.domain

import java.util.*

interface WorldRepository {

    fun add(world: World)

    fun remove(world: World)

    fun update(world: World)

    fun getAll(): List<World>

    fun find(world: UUID): World
}