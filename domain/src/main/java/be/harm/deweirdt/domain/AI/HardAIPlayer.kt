package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position

internal class HardAIPlayer(
    private val game: Game
) : AIPlayer {

    /**
     * Calculates the next move for the current player in the [game].
     */
    override fun getNextMove(): Position {
        var bestScoreSoFar: Int = Int.MIN_VALUE
        var bestMoveSoFar: Position? = null
        for (position in game.board.emptyFields) {
            game.board.placeSymbol(game.currentPlayer.symbol, position)
            val moveValue = minimax(game.board, 0, isMaximizingPlayer = false)
            if (moveValue > bestScoreSoFar) {
                bestScoreSoFar = moveValue
                bestMoveSoFar = position
            }
            game.board.removeSymbol(position)
        }
        return bestMoveSoFar!!
    }

    private fun minimax(
        board: Board,
        depth: Int,
        isMaximizingPlayer: Boolean
    ): Int {
        if (Game(board).isOver) {
            return calculateFinalScore(board, isMaximizingPlayer, depth)
        }

        var bestScoreSoFar: Int
        if (isMaximizingPlayer) {
            bestScoreSoFar = Int.MIN_VALUE
            for (position in board.emptyFields) {
                board.placeSymbol(game.currentPlayer.symbol, position)
                val newScore = minimax(board, depth + 1, isMaximizingPlayer.not())
                if (newScore > bestScoreSoFar) {
                    bestScoreSoFar = newScore
                }
                board.removeSymbol(position)
            }
            return bestScoreSoFar
        } else {
            bestScoreSoFar = Int.MAX_VALUE
            for (position in board.emptyFields) {
                board.placeSymbol(game.currentPlayer.symbol, position)
                val newScore = minimax(board, depth + 1, isMaximizingPlayer.not())
                if (newScore < bestScoreSoFar) {
                    bestScoreSoFar = newScore
                }
                board.removeSymbol(position)
            }
            return bestScoreSoFar
        }
    }

    private fun calculateFinalScore(
        board: Board,
        isMaximizingPlayer: Boolean,
        depth: Int
    ): Int {
        var score = BoardEvaluator.calculateScore(
            board,
            game.currentPlayer.symbol,
            isMaximizingPlayer
        )
        score = modifyScoreToEnsureQuickestWin(score, depth, isMaximizingPlayer)
        return score
    }

    private fun modifyScoreToEnsureQuickestWin(
        score: Int,
        depth: Int,
        isMaximizingPlayer: Boolean
    ): Int {
        return if (isMaximizingPlayer) {
            score - depth
        } else {
            score + depth
        }
    }
}
