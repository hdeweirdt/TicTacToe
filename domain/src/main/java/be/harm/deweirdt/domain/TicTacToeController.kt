package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.AI.AIPlayer
import be.harm.deweirdt.domain.AI.AdjustableDifficultyAI
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Player
import be.harm.deweirdt.domain.game.Position

class TicTacToeController {
    private lateinit var game: Game

    private lateinit var aiPlayer: AIPlayer

    private lateinit var humanPlayer: Player
    private lateinit var computerPlayer: Player

    // Initially false so it is true the first time playing the game.
    private var humanGoesFirst = false

    fun startNewGame(difficulty: Difficulty = Difficulty.EASY) {
        game = Game()
        aiPlayer = AdjustableDifficultyAI(game, difficulty)

        setUpPlayers()
    }

    fun setDifficulty(chosenDifficulty: Difficulty) {
        aiPlayer.difficulty = chosenDifficulty
    }

    private fun setUpPlayers() {
        switchSides()
        if (humanGoesFirst) {
            humanPlayer = game.currentPlayer
            computerPlayer = game.otherPlayer
        } else {
            computerPlayer = game.currentPlayer
            humanPlayer = game.otherPlayer
        }
    }

    private fun switchSides() {
        humanGoesFirst = !humanGoesFirst
    }

    fun humanMove(rowIndex: Int, columnIndex: Int) {
        if (game.currentPlayer == humanPlayer) {
            val position = Position(rowIndex, columnIndex)
            game.currentPlayerMove(position)
        }
    }

    fun computerMove() {
        if (game.currentPlayer == computerPlayer) {
            val aiMove = aiPlayer.getNextMove()
            game.currentPlayerMove(aiMove)
        }
    }

    fun getCurrentBoard(): Array<Array<Char>> {
        val board = game.board
        return Array(board.dimension) { row -> Array(board.dimension) { column -> board[row, column].symbol } }
    }

    fun getCurrentPlayerName(): String {
        if (game.currentPlayer == humanPlayer) {
            return "Human"
        } else {
            return "Computer"
        }
    }

    fun getCurrentPlayerSymbol(): Char {
        return game.currentPlayer.symbol
    }

    val isGameOver
        get() = game.isOver

    val isComputerTurn
        get() = game.currentPlayer == computerPlayer

    val isDraw
        get() = game.isDraw

    /**
     * Returns the symbol of the winning player, and null if there is no winning player yet.
     */
    fun getWinningPlayerName(): String? {
        if (game.winningPlayer == null) {
            return null
        } else {
            return if (game.winningPlayer == humanPlayer) "Human" else "Computer"
        }
    }
}
