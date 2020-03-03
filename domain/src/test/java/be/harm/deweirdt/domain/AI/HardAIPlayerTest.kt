package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Game
import be.harm.deweirdt.domain.game.Position
import org.junit.Assert.assertEquals
import org.junit.Test

class HardAIPlayerTest {
    private lateinit var aiPlayer: HardAIPlayer

    @Test
    fun `HardAIPlayer completes a vertical 3-in-a-row to make the winning move`() {
        // Arrange
        val board = Board(
            """
            XOX
            XOO
            .X.
            """.trimIndent()
        )
        aiPlayer = HardAIPlayer(Game(board))

        // Act
        val nextMove = aiPlayer.getNextMove()

        assertEquals(Position(2, 0), nextMove)
    }

    @Test
    fun `HardAIPlayer completes a horizontal 3-in-a-row in order to win`() {
        // Arrange
        val board = Board(
            """
            XX.
            OO.
            ...
            """.trimIndent()
        )
        aiPlayer = HardAIPlayer(Game(board))

        // Act
        val nextMove = aiPlayer.getNextMove()

        assertEquals(Position(0, 2), nextMove)
    }

    @Test
    fun `HardAIPlayer completes a diagonal 3-in-a-row in order to win`() {
        // Arrange
        val board = Board(
            """
            XXO
            OXO
            .O.
            """.trimIndent()
        )
        aiPlayer = HardAIPlayer(Game(board))

        // Act
        val nextMove = aiPlayer.getNextMove()

        assertEquals(Position(2, 2), nextMove)
    }
}
