package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board

internal object BoardEvaluator {
    // TODO: make a memoizing version of this. Would require to first calculate/remind the score, then
    // add/subtract the depth. Could also mirror the score of max/minimizersymbol by negating the
    // resulting score.
    fun evaluate(board: Board, maximizerSymbol: Char, minimizerSymbol: Char, depth: Int): Int {
        when {
            board.symbolFillsAColumn(maximizerSymbol) -> {
                return 10 - depth
            }
            board.symbolFillsARow(maximizerSymbol) -> {
                return 10 - depth
            }
            board.symbolFilsADiagonal(maximizerSymbol) -> {
                return 10 - depth
            }
            board.symbolFillsAColumn(minimizerSymbol) -> {
                return -10 + depth
            }
            board.symbolFillsARow(minimizerSymbol) -> {
                return -10 + depth
            }
            board.symbolFilsADiagonal(minimizerSymbol) -> {
                return -10 + depth
            }
            else -> return 0
        }
    }
}