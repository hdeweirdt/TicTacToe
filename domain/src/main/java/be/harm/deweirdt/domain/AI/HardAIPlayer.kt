package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position
import kotlin.math.max
import kotlin.math.min

internal class HardAIPlayer(
    private val game: Game
) : AIPlayer {

    private val maximizingPlayerSymbol
        get() = game.currentPlayer.symbol
    private val minimizingPlayerSymbol
        get() = game.otherPlayer.symbol

    /**
     * Calculates the next move for the current player in the [game].
     */
    override fun getNextMove(): Position {
        var bestScoreSoFar: Int = Int.MIN_VALUE
        var bestMoveSoFar: Position? = null
        for (move in game.board.emptyFields) {
            game.board.placeSymbol(game.currentPlayer.symbol, move)
            val moveScore = minimax(game.board, 0, isMaximizingPlayer = false)
            game.board.removeSymbol(move)
            if (moveScore > bestScoreSoFar) {
                bestScoreSoFar = moveScore
                bestMoveSoFar = move
            }
        }
        return bestMoveSoFar!!
    }

    private fun minimax(
        board: Board,
        depth: Int,
        isMaximizingPlayer: Boolean
    ): Int {
        if (Game(board).isOver) {
            return calculateFinalScore(board, depth)
        }

        var bestScoreSoFar: Int
        if (isMaximizingPlayer) {
            bestScoreSoFar = Int.MIN_VALUE
            for (position in board.emptyFields) {
                board.placeSymbol(maximizingPlayerSymbol, position)
                val newScore = minimax(board, depth + 1, !isMaximizingPlayer)
                board.removeSymbol(position)
                bestScoreSoFar = max(bestScoreSoFar, newScore)
            }
            return bestScoreSoFar
        } else {
            bestScoreSoFar = Int.MAX_VALUE
            for (position in board.emptyFields) {
                board.placeSymbol(minimizingPlayerSymbol, position)
                val newScore = minimax(board, depth + 1, !isMaximizingPlayer)
                board.removeSymbol(position)
                bestScoreSoFar = min(bestScoreSoFar, newScore)
            }
            return bestScoreSoFar
        }
    }

    private fun calculateFinalScore(
        board: Board,
        depth: Int
    ): Int {
        return BoardEvaluator.evaluate(
            board,
            maximizingPlayerSymbol,
            minimizingPlayerSymbol,
            depth
        )
    }
}
