package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board

object BoardEvaluator {
    // TODO: memoization?

    /**
     * Evaluates the state of the given [board], from the perspective of the maximizing
     * or minimizing player (indicated by [isMaximizingPlayer].
     */
    fun calculateScore(board: Board, playerSymbol: Char, isMaximizingPlayer: Boolean): Int {
        if (isMaximizingPlayer) {
            return calculateForMaximizingPlayer(board, playerSymbol)
        } else {
            return calculateForMinimizingPlayer(board, playerSymbol)
        }
    }

    private fun calculateForMinimizingPlayer(
        board: Board,
        playerSymbol: Char
    ): Int {
        when {
            board.symbolFillsAColumn(playerSymbol) -> {
                return -10
            }
            board.symbolFillsARow(playerSymbol) -> {
                return -10
            }
            board.symbolFilsADiagonal(playerSymbol) -> {
                return -10
            }
            else -> return 0
        }
    }

    private fun calculateForMaximizingPlayer(
        board: Board,
        playerSymbol: Char
    ): Int {
        when {
            board.symbolFillsAColumn(playerSymbol) -> {
                return 10
            }
            board.symbolFillsARow(playerSymbol) -> {
                return 10
            }
            board.symbolFilsADiagonal(playerSymbol) -> {
                return 10
            }
            else -> return 0
        }
    }
}
