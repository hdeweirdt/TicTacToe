package be.harm.deweirdt.domain

private const val NUMBER_OF_PLAYERS = 2

class Game {

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

    private val secondPlayerWon
        get() = hasWon(players[0])

    private val firstPlayerWon
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

    private fun nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS
    }

    /**
     * Makes the current player place place his symbol on the given location.
     * Also switches [currentPlayer] to the next [Player].
     *
     * @throws IllegalArgumentException when the location already has a non-empty symbol.
     */
    fun currentPlayerMove(rowIndex: Int, columnIndex: Int) {
        val symbol = currentPlayer.symbol
        board.placeSymbol(symbol, rowIndex, columnIndex)
        nextPlayer()
    }

    // For testing purposes only
    internal constructor(board: Board) {
        this.board = board
    }

    override fun toString(): String {
        return "$board\nCurrent Player:${currentPlayer.symbol}\nGame over? $isOver"
    }
}
