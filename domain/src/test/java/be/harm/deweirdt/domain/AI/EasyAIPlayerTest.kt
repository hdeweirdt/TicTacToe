package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.game.Board
import org.junit.Assert.assertTrue
import org.junit.Test

class EasyAIPlayerTest {

    private val player = EasyAIPlayer()

    @Test
    fun `the easy AI always places its symbol in an empty field of the board`() {
        // Arrange
        val board = Board(
            """
            X..
            ..O
            X..
            """.trimIndent()
        )

        // Repeat many times because the be.harm.deweirdt.domain.AI is inherently random
        repeat(100) {
            // Act
            val move = player.getNextMove(board)

            assertTrue(board.emptyFields.contains(move))
        }
    }
}
