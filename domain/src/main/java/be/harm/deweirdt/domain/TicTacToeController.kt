package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position

class TicTacToeController {
    private lateinit var game: Game

    fun startNewGame() {
        game = Game()
    }

    fun currentPlayerMove(rowIndex: Int, columnIndex: Int) {
        val position = Position(rowIndex, columnIndex)
        game.currentPlayerMove(position)
    }

    fun getCurrentBoard(): Array<Array<Char>> {
        val board = game.board
        return Array(board.dimension) { row -> Array(board.dimension) { column -> board[row, column].symbol } }
    }

    fun getCurrentPlayerSymbol(): Char {
        return game.currentPlayer.symbol
    }

    fun isGameOver(): Boolean {
        return game.isOver
    }

    /**
     * Returns the symbol of the winning player, and null if there is no winning player yet.
     */
    fun getWinningPlayerSymbol(): Char? {
        return game.winningPlayer?.symbol
    }

}
