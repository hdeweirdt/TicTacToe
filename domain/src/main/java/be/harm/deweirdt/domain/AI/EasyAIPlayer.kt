package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Position

/**
 * A (very) easy Tic-Tac-Toe player that places its symbols randomly on the board.
 */
class EasyAIPlayer : AIPlayer {
    override fun getNextMove(currentBoard: Board): Position {
        val emptyFields = currentBoard.emptyFields
        return emptyFields.random()
    }
}
