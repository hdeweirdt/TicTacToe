package be.harm.deweirdt.domain

private const val NUMBER_OF_PLAYERS = 2

class Game {

    val isOver: Boolean
        get() {
            if (board.isFull) {
                return true
            } else {
                return false
            }
        }

    val board = Board()

    val players = arrayOf(
        Player(SYMBOL_PLAYER_X),
        Player(SYMBOL_PLAYER_O)
    )

    private var currentPlayerIndex = 0

    val currentPlayer: Player
        get() = players[currentPlayerIndex]

    private fun nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS
    }

    /**
     * Makes the current player place place his symbol on the given location.
     * Also switches [currentPlayer] tot the next [Player].
     *
     * @throws IllegalArgumentException when the location already has a non-empty symbol.
     */
    fun makeMove(rowIndex: Int, columnIndex: Int) {
        val symbol = currentPlayer.symbol
        board.placeSymbol(symbol, rowIndex, columnIndex)
        nextPlayer()
    }
}
