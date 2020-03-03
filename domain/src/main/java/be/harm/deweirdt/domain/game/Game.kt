package be.harm.deweirdt.domain.game

private const val NUMBER_OF_PLAYERS = 2

internal class Game {

    constructor() {
        this.board = Board()
    }

    val board: Board

    val players = arrayOf(
        Player(SYMBOL_PLAYER_X),
        Player(SYMBOL_PLAYER_O)
    )

    val isOver: Boolean
        get() {
            return firstPlayerWon || secondPlayerWon || board.isFull
        }

    val isDraw: Boolean
        get() {
            return board.isFull && !firstPlayerWon && !secondPlayerWon
        }

    val winningPlayer: Player?
        get() {
            if (firstPlayerWon) return players[0]
            if (secondPlayerWon) return players[1]
            return null
        }

    private val firstPlayerWon
        get() = hasWon(players[0])

    private val secondPlayerWon
        get() = hasWon(players[1])

    private fun hasWon(player: Player): Boolean {
        val playerSymbol = player.symbol
        return board.symbolFillsARow(playerSymbol) ||
                board.symbolFillsAColumn(playerSymbol) ||
                board.symbolFilsADiagonal(playerSymbol)
    }

    private var currentPlayerIndex = 0

    val currentPlayer: Player
        get() = players[currentPlayerIndex]

    val otherPlayer: Player
        get() = players[(currentPlayerIndex + 1) % NUMBER_OF_PLAYERS]

    private fun switchToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS
    }

    /**
     * Makes the current player place place his symbol on the given location.
     * Also switches [currentPlayer] to the next [Player] as long as the game is not over.
     *
     * @throws IllegalArgumentException when the location already has a non-empty symbol.
     * @throws IllegalStateException when the game was already finished
     */
    fun currentPlayerMove(position: Position) {
        if (isOver) {
            throw IllegalStateException("Game is already over, you can't make any more moves.")
        }
        board.placeSymbol(currentPlayer.symbol, position)
        if (!isOver) {
            switchToNextPlayer()
        }
    }

    internal constructor(board: Board) {
        this.board = board
    }

    override fun toString(): String {
        return "$board\nCurrent Player:${currentPlayer.symbol}\nGame over? $isOver"
    }
}
