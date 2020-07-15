package io.glassapp.ddd.oo.gameoflife.infrastructure

import io.glassapp.ddd.oo.gameoflife.domain.World
import io.glassapp.ddd.oo.gameoflife.domain.WorldRepository
import java.util.*

class InMemoryWorldRepository : WorldRepository {
    private val worldMap = hashMapOf<UUID, World>()

    override fun add(world: World) {
        worldMap[world.id] = world
    }

    override fun remove(world: World) {
        worldMap.remove(world.id)
    }

    override fun update(world: World) {
        worldMap[world.id] = world
    }

    override fun getAll(): List<World> {
        return worldMap.values.toList()
    }

    override fun find(worldId: UUID): World {
        //TODO: add Optional
        return worldMap[worldId]!!
    }
}