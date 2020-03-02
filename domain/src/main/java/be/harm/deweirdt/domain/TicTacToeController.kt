package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.AI.AIPlayer
import be.harm.deweirdt.domain.AI.HardAIPlayer
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position

class TicTacToeController {
    private lateinit var game: Game

    private lateinit var aiPlayer: AIPlayer

    // Starts as false because the value is inverted when starting a new Game
    private var playerGoesFirst = false

    private var playersTurn = true

    init {
        startNewGame()
    }

    fun startNewGame() {
        // Who goes first changes every new game.
        playerGoesFirst = !playerGoesFirst
        game = Game(if (playerGoesFirst) 0 else 1)
        aiPlayer = HardAIPlayer(game)
        playersTurn = playerGoesFirst
    }

    fun playerMove(rowIndex: Int, columnIndex: Int) {
        if (playersTurn) {
            val position = Position(rowIndex, columnIndex)
            game.currentPlayerMove(position)
            playersTurn = false
        }
    }

    fun aiMove() {
        if (!playersTurn) {
            val aiMove = aiPlayer.getNextMove()
            game.currentPlayerMove(aiMove)
            playersTurn = true
        }
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

    fun isDraw(): Boolean {
        return game.isDraw
    }

    /**
     * Returns the symbol of the winning player, and null if there is no winning player yet.
     */
    fun getWinningPlayerSymbol(): Char? {
        return game.winningPlayer?.symbol
    }
}
