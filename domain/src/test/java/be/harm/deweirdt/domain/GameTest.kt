package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Player
import be.harm.deweirdt.domain.game.Position
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTestRule

class GameTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(domainModule)
    }

    private lateinit var game: Game

    @Test
    fun `when a game is started there are two players in the game`() {
        // Arrange
        game = Game()

        // Assert
        assertEquals(2, game.players.size)
    }

    @Test
    fun `player X always has the first move in a game`() {
        // Arrange
        game = Game()

        // Assert
        assertEquals(game.currentPlayer.symbol, 'X')
    }

    @Test
    fun `a player making a move automatically results in the current player being updated to the other player`() {
        // Arrange
        game = Game()
        val firstPlayer = game.currentPlayer

        // Act
        game.currentPlayerMove(Position(0, 0))

        // Assert
        assertNotEquals(firstPlayer, game.currentPlayer)
    }

    @Test
    fun `making a move as a player results in that player's symbol being shown on the board`() {
        // Arrange
        game = Game()

        // Act
        val currentPlayerSymbol = game.currentPlayer.symbol
        game.currentPlayerMove(Position(0, 0))

        // Assert
        assertEquals(currentPlayerSymbol, game.board[0, 0].symbol)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a player placing his symbol on an already occupied place results in an error`() {
        // Arrange
        val board = Board(
            """
            XO.
            ...
            ...
            """.trimIndent()
        )
        game = Game(board)

        // Act
        game.currentPlayerMove(Position(0, 0))
    }

    @Test
    fun `a game is over when the board is full`() {
        // Arrange
        val board = Board(
            """
            XOX
            XOX
            XOX
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val isOver = game.isOver

        // Assert
        assertTrue(isOver)
    }

    @Test
    fun `a game is over when one of the players has a horizontal three-in-a-row`() {
        // Arrange
        val board = Board(
            """
            XOX
            XXX
            XOO
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val isOver = game.isOver

        assertTrue(isOver)
    }

    @Test
    fun `a game is over when one of the players has a diagonal three-in-a-row`() {
        // Arrange
        val board = Board(
            """
            XOO
            OXX
            XOX
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val isOver = game.isOver

        assertTrue(isOver)
    }

    @Test
    fun `a game is over when one of the players has a vertical three-in-a-row`() {
        // Arrange
        val board = Board(
            """
            XOX
            OOX
            XOX
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val isOver = game.isOver

        assertTrue(isOver)
    }

    @Test
    fun `an game where no-one has made a move is not over`() {
        // Arrange
        game = Game()

        // Assert
        assertFalse(game.isOver)
    }

    @Test
    fun `when a game-finishing move is made the game does not advance to the next player`() {
        // Arrange
        val board = Board(
            """
            XO.
            .XO
            .O.
            """.trimIndent()
        )
        game = Game(board)
        val currentPlayer = game.currentPlayer

        // Act
        game.currentPlayerMove(Position(2, 2))

        // Assert
        assertTrue(game.isOver)
        assertEquals(currentPlayer, game.currentPlayer)
    }

    @Test
    fun `isDraw indicates when a game is finished and there is no winning player`() {
        // Arrange
        val board = Board(
            """
            XOO
            OXX
            XOO
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val isOver = game.isDraw

        assertTrue(isOver)
    }

    @Test
    fun `winningPlayer indicates the winning player`() {
        // Arrange
        val board = Board(
            """
            X..
            OX.
            XOX
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val winningPlayer = game.winningPlayer

        // Assert
        assertEquals(Player('X'), winningPlayer)
    }
    @Test
    fun `winningPlayer is null when the game ends in a draw`() {
        // Arrange
        val board = Board(
            """
            XOX
            XXO
            OXO
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val winningPlayer = game.winningPlayer

        // Assert
        assertNull(winningPlayer)
    }

    @Test
    fun `winningPlayer is null when no player has won the game yet`() {
        // Arrange
        val board = Board(
            """
            X..
            OX.
            XO.
            """.trimIndent()
        )
        game = Game(board)

        // Act
        val winningPlayer = game.winningPlayer

        // Assert
        assertNull(winningPlayer)
    }
}
