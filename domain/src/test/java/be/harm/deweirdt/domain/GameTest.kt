package be.harm.deweirdt.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    @Test
    fun `when a game is started there are two players in the game`() {
        assertEquals(2, game.players.size)
    }

    @Test
    fun `player X always has the first move in a game`() {
        assertEquals(game.currentPlayer.symbol, 'X')
    }

    @Test
    fun `a player making a move automatically results in the current player being updated to the other player`() {
        // Arrange
        val firstPlayer = game.currentPlayer

        // Act
        game.makeMove(0, 0)

        // Assert
        assertNotEquals(firstPlayer, game.currentPlayer)
    }

    @Test
    fun `making a move as a player results in that player's symbol being shown on the board`() {
        // Act
        val currentPlayerSymbol = game.currentPlayer.symbol
        game.makeMove(0, 0)

        // Assert
        assertEquals(currentPlayerSymbol, game.board[0, 0].symbol)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a player placing his symbol on an already occupied place results in an error`() {
        // Arrange
        game.makeMove(0, 0)

        // Act
        game.makeMove(0, 0)
    }

    @Test
    fun `a game is over when the board is full`() {
        // Arrange
        for (rowIndex in 0 until DIMENSION) {
            for (columnIndex in 0 until DIMENSION) {
                game.makeMove(rowIndex, columnIndex)
            }
        }

        // Act
        val isOver = game.isOver

        // Assert
        assertTrue(isOver)
    }

    @Test
    fun `a game is over when one of the players has a horizontal three-in-a-row`() {
    }

    @Test
    fun `a game is over when one of the players has a diagonal three-in-a-row`() {
    }

    @Test
    fun `a game is over when one of the players has a vertical three-in-a-row`() {
    }

    @Test
    fun `an game where noone has made a move is not over`() {
        assertFalse(game.isOver)
    }
}
