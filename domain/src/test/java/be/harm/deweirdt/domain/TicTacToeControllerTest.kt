package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Player
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class TicTacToeControllerTest : KoinTest {

    private lateinit var controller: TicTacToeController

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(domainModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        mockkClass(it)
    }

    @Before
    fun setUp() {
        controller = TicTacToeController()
        controller.startNewGame()
    }

    @Test
    fun `after every game the player that starts switches around`() {
        // Arrange
        val computersTurn = controller.isComputerTurn

        // Act
        controller.startNewGame()

        // Assert
        assertNotEquals(computersTurn, controller.isComputerTurn)
    }

    @Test
    fun `isOver indicates when the game is over`() {
        // Arrange
        declareMock<Game> {
            every { isOver } returns true
        }
        controller.startNewGame()

        // Act
        val isOver = controller.isGameOver

        // Assert
        assertTrue(isOver)
    }

    @Test
    fun `isOver indicates when the game is not over`() {
        // Arrange
        declareMock<Game> {
            every { currentPlayer } returns Player('X')
            every { otherPlayer } returns Player('O')
            every { isOver } returns false
        }
        controller.startNewGame()

        // Act
        val isOver = controller.isGameOver

        // Assert
        assertFalse(isOver)
    }

    @Test
    fun `the winning players name is not known when the game is not over yet`() {
        // Arrange
        declareMock<Game> {
            every { currentPlayer } returns Player('X')
            every { otherPlayer } returns Player('O')
            every { isOver } returns false
        }

        // Act
        val winningPlayerName = controller.getWinningPlayerName()

        // Assert
        assertNull(winningPlayerName)
    }

    @Test
    fun `the winning players name is the name of the winning player in the game`() {
        // Arrange
        declareMock<Game> {
            every { currentPlayer } returns Player('X')
            every { otherPlayer } returns Player('O')
            every { isOver } returns true
            every { winningPlayer } returns currentPlayer
        }
        controller.startNewGame()

        // Act
        val winningPlayerName = controller.getWinningPlayerName()

        // Assert
        assertNotNull(winningPlayerName)
    }

    @Test
    fun `the human player goes first in the first game`() {
        // Arrange
        declareMock<Game> {
            every { currentPlayer } returns Player('X')
            every { otherPlayer } returns Player('O')
        }

        // Act
        val isHumanTurn = !controller.isComputerTurn
        val currentPlayerName = controller.getCurrentPlayerName()

        // Assert
        assertTrue(isHumanTurn)
        assertEquals(currentPlayerName, "Human")
    }
}
