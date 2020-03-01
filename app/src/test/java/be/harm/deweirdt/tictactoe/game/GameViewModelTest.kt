package be.harm.deweirdt.tictactoe.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import be.harm.deweirdt.tictactoe.getOrAwaitValue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel()
    }

    @Test
    fun `the winning player text is invisible when the winning player is not set`() {
        // Act
        val isVisible = viewModel.winningPlayerVisible.getOrAwaitValue()

        // Assert
        assertFalse(isVisible)
    }

    @Test
    fun `the winning player text is visible when the winning player is set`() {
        // Arrange
        viewModel.setWinningPlayer("X")

        // Act
        val isVisible = viewModel.winningPlayerVisible.getOrAwaitValue()

        // Assert
        assertTrue(isVisible)
    }
}
