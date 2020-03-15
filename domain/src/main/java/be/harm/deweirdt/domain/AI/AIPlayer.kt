package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.Difficulty
import be.harm.deweirdt.domain.game.Position

/**
 * Interface for classes that represent an AI Tic-tac-toe player.
 * By design, the difficulty should be changeable.
 */
internal interface AIPlayer {
    fun getNextMove(): Position

    var difficulty: Difficulty
}
