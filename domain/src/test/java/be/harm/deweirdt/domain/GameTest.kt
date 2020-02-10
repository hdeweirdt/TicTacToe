package be.harm.deweirdt.domain

import junit.framework.Assert.assertEquals
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
    fun `switching to the next player when the current player is X makes O the current player`() {
        // Act
        game.nextPlayer()

        // Assert
        assertEquals(game.currentPlayer.symbol, 'O')
    }

    @Test
    fun `switching to the next player when the current player is O makes X the current player`() {
        // Act
        game.nextPlayer()
        game.nextPlayer()

        // Assert
        assertEquals(game.currentPlayer.symbol, 'X')
    }
}
