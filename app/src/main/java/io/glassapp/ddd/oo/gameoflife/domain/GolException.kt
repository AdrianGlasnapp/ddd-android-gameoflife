package io.glassapp.ddd.oo.gameoflife.domain

import java.lang.RuntimeException

sealed class GolException : RuntimeException()

class WorldNotFoundException : GolException()