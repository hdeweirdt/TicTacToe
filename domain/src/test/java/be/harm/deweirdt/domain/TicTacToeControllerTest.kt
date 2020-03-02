package be.harm.deweirdt.domain

import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class TicTacToeControllerTest {

    private lateinit var controller: TicTacToeController

    @Before
    fun setUp() {
        controller = TicTacToeController()
    }

    @Test
    fun `after finishing a game the player that started first must start second`() {
        // Arrange
        val initialBeginningPlayer = controller.getCurrentPlayerSymbol()

        // Act
        controller.startNewGame()
        val currentInitialPlayer = controller.getCurrentPlayerSymbol()

        // Assert
        assertNotEquals(initialBeginningPlayer, currentInitialPlayer)
    }
}