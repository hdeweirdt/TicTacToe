package AI

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Position

interface AIPlayer {
    fun getNextMove(currentBoard: Board): Position
}
