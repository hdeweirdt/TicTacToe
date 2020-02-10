package be.harm.deweirdt.domain

private const val SYMBOL_PLAYER_X = 'X'
private const val SYMBOL_PLAYER_O = 'O'
private const val NUMBER_OF_PLAYERS = 2

class Game {

    val players = arrayOf(
        Player(SYMBOL_PLAYER_X),
        Player(SYMBOL_PLAYER_O)
    )

    private var currentPlayerIndex = 0

    val currentPlayer: Player
        get() = players[currentPlayerIndex]

    fun nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % NUMBER_OF_PLAYERS
    }
}
