package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Position

interface AIPlayer {
    fun getNextMove(): Position
}
