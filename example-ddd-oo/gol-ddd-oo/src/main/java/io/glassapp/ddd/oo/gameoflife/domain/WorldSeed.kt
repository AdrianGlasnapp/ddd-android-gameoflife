package io.glassapp.ddd.oo.gameoflife.domain

class WorldSeed(val rows: Int, val columns: Int, val livingCells: Set<Position> = setOf())