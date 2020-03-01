package be.harm.deweirdt.domain.game

/**
 * Represents a position on a [Board].
 * There are no guarantees that this position will be a valid position in/on the board.
 */
data class Position(
    val row: Int,
    val column: Int
)
