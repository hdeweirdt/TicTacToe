package be.harm.deweirdt.domain

import io.mockk.mockkClass
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule

class TicTacToeControllerTest : KoinTest {

    private lateinit var controller: TicTacToeController

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(domainModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {
        mockkClass(it, relaxed = true)
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
}
