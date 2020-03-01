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
            val newBoard = game.board.copy()
            newBoard.placeSymbol(game.currentPlayer.symbol, position)
            val moveValue = minimax(game.board, 0, isMaximizingPlayer = false)
            if (moveValue > bestScoreSoFar) {
                bestScoreSoFar = moveValue
                bestMoveSoFar = position
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
            return calculateFinalScore(board, isMaximizingPlayer, depth)
        }

        var bestScoreSoFar: Int
        if (isMaximizingPlayer) {
            bestScoreSoFar = Int.MIN_VALUE
            for (position in board.emptyFields) {
                println("maximizer trying $position from ${board.emptyFields}")
                val newBoard = board.copy()
                newBoard.placeSymbol(game.currentPlayer.symbol, position)
                val newScore = minimax(newBoard, depth + 1, isMaximizingPlayer.not())
                println("Score found for $position: $newScore, bestSoFar $bestScoreSoFar")
                if (newScore > bestScoreSoFar) {
                    bestScoreSoFar = newScore
                }
            }
            return bestScoreSoFar
        } else {
            bestScoreSoFar = Int.MAX_VALUE
            for (position in board.emptyFields) {
                println("minimizer trying $position from ${board.emptyFields}")
                val newBoard = board.copy()
                newBoard.placeSymbol(game.otherPlayer.symbol, position)
                val newScore = minimax(newBoard, depth + 1, isMaximizingPlayer.not())
                println("Score found for $position: $newScore, bestSoFar $bestScoreSoFar")
                if (newScore < bestScoreSoFar) {
                    bestScoreSoFar = newScore
                }
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
