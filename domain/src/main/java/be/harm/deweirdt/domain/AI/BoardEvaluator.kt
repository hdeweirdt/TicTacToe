package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board

internal object BoardEvaluator {
    fun evaluate(board: Board, maximizerSymbol: Char, minimizerSymbol: Char, depth: Int): Int {
        when {
            board.symbolFillsAColumn(maximizerSymbol) -> {
                return 10-depth
            }
            board.symbolFillsARow(maximizerSymbol) -> {
                return 10-depth
            }
            board.symbolFilsADiagonal(maximizerSymbol) -> {
                return 10-depth
            }
            board.symbolFillsAColumn(minimizerSymbol) -> {
                return -10+depth
            }
            board.symbolFillsARow(minimizerSymbol) -> {
                return -10+depth
            }
            board.symbolFilsADiagonal(minimizerSymbol) -> {
                return -10+depth
            }
            else -> return 0
        }
    }
}