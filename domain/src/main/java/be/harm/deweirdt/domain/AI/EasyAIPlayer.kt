package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position

/**
 * A (very) easy Tic-Tac-Toe player that places its symbols randomly on the board.
 */
class EasyAIPlayer(private val game: Game) : AIPlayer {
    override fun getNextMove(): Position {
        val emptyFields = game.board.emptyFields
        return emptyFields.random()
    }
}
