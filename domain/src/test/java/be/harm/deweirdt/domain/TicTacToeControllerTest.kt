package be.harm.deweirdt.domain

import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class TicTacToeControllerTest {

    private lateinit var controller: TicTacToeController

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
}
