package io.glassapp.ddd.oo.gameoflife

import io.glassapp.ddd.oo.gameoflife.application.GameService
import io.glassapp.ddd.oo.gameoflife.domain.WorldRepository
import io.glassapp.ddd.oo.gameoflife.infrastructure.InMemoryWorldRepository

class GofContext {

    val gameService: GameService by lazy {
        GameService(createWorldRepository())
    }

    private fun createWorldRepository(): WorldRepository {
        return InMemoryWorldRepository()
    }
}