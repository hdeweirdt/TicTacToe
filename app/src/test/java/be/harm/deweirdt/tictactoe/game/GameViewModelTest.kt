package be.harm.deweirdt.tictactoe.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.harm.deweirdt.domain.Difficulty
import be.harm.deweirdt.domain.TicTacToeController
import be.harm.deweirdt.tictactoe.CoroutineTestRule
import be.harm.deweirdt.tictactoe.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: GameViewModel

    private val controller = mockk<TicTacToeController>(relaxed = true)

    @Before
    fun setUp() {
        viewModel = GameViewModel(controller)
    }

    @Test
    fun `the winning player text is invisible when there is no winning player`() {
        // Arrange
        every { controller.getWinningPlayerSymbol() } returns null

        // Act
        viewModel.updateUIState()
        val isVisible = viewModel.winningPlayerVisible.getOrAwaitValue()

        // Assert
        assertFalse(isVisible)
    }

    @Test
    fun `the winning player text is visible when the winning player is known`() {
        // Arrange
        every { controller.getWinningPlayerSymbol() } returns 'X'

        // Act
        viewModel.updateUIState()
        val isVisible = viewModel.winningPlayerVisible.getOrAwaitValue()

        // Assert
        assertTrue(isVisible)
    }

    @Test
    fun `the position chosen by the player is passed to the controller`() {
        // Act
        viewModel.positionChosen(1, 1)

        // Assert
        verify { controller.humanMove(1, 1) }
    }

    @Test
    fun `when changing the difficulty to hard this change is passed to the controller`() {
        // Act
        viewModel.onCheckedHardDifficulty(true)

        // Assert
        verify { controller.setDifficulty(Difficulty.HARD) }
    }

    @Test
    fun `when changing the difficulty to easy this change is passed to the controller`() {
        // Act
        viewModel.onCheckedHardDifficulty(false)

        // Assert
        verify { controller.setDifficulty(Difficulty.EASY) }
    }
}
