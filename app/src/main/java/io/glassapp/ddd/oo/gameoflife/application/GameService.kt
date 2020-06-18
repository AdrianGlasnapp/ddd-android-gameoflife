package io.glassapp.ddd.oo.gameoflife.application

import io.glassapp.ddd.oo.gameoflife.domain.World
import io.glassapp.ddd.oo.gameoflife.domain.WorldData
import io.glassapp.ddd.oo.gameoflife.domain.WorldRepository

class GameService(private val worldRepository: WorldRepository) {

    fun createNewWorld(rows: Int, columns: Int): WorldData {
        val world = World.newWorld(rows, columns)

        worldRepository.add(world)

        return world.data()
    }


}