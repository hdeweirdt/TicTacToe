package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Position
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BoardTest {
    private lateinit var board: Board

    @Test
    fun `the default board consists of 3 by 3 fields`() {
        // Act
        board = Board()

        // Assert
        assertEquals(3, board.fields.size)

        assertEquals(3, board.fields[0].size)
        assertEquals(3, board.fields[1].size)
        assertEquals(3, board.fields[2].size)
    }

    @Test
    fun `a board can be constructed from a string representation`() {
        // Arrange
        val inputString = """
            X.X
            X.O
            O.X
        """.trimIndent()

        // Act
        val resultingBoard = Board(inputString)

        // Assert
        assertEquals('X', resultingBoard.fields[0][0].symbol)
        assertEquals('.', resultingBoard.fields[0][1].symbol)
        assertEquals('X', resultingBoard.fields[0][2].symbol)

        assertEquals('X', resultingBoard.fields[1][0].symbol)
        assertEquals('.', resultingBoard.fields[1][1].symbol)
        assertEquals('O', resultingBoard.fields[1][2].symbol)

        assertEquals('O', resultingBoard.fields[2][0].symbol)
        assertEquals('.', resultingBoard.fields[2][1].symbol)
        assertEquals('X', resultingBoard.fields[2][2].symbol)
    }

    @Test(expected = AssertionError::class)
    fun `a board can not be constructed from an invalid string representation`() {
        // Arrange
        val inputString =
            """
            X.
            X.O
            O.X
            """.trimIndent()

        // Act
        val resultingBoard = Board(inputString)

        // Assert
        assertEquals('X', resultingBoard.fields[0][0].symbol)
        assertEquals('.', resultingBoard.fields[0][1].symbol)
        assertEquals('X', resultingBoard.fields[0][2].symbol)

        assertEquals('X', resultingBoard.fields[1][0].symbol)
        assertEquals('.', resultingBoard.fields[1][1].symbol)
        assertEquals('O', resultingBoard.fields[1][2].symbol)

        assertEquals('O', resultingBoard.fields[2][0].symbol)
        assertEquals('.', resultingBoard.fields[2][1].symbol)
        assertEquals('X', resultingBoard.fields[2][2].symbol)
    }

    @Test
    fun `a board is considered full when all fields are not empty`() {
        // Arrange
        val board = Board(
            """
            XOX
            XXX
            OOO
            """.trimIndent()
        )

        // Act
        val isFull = board.isFull

        // Assert
        assertTrue(isFull)
    }

    @Test
    fun `a board is not full as soon as one field is not empty`() {
        // Arrange
        val board = Board(
            """
            XOX
            X.X
            XOO
            """.trimIndent()
        )

        // Act
        val isFull = board.isFull

        // Assert
        assertFalse(isFull)
    }

    @Test
    fun `an empty board is considered not full`() {
        val board = Board(
            """
            ...
            ...
            ...
            """.trimIndent()
        )

        assertFalse(board.isFull)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `placing a symbol on top of another symbol results in an error`() {
        // Arrange
        board = Board(
            """
            X..
            ...
            ...
            """.trimIndent()
        )

        // Act
        board.placeSymbol('O', 0, 0)
    }

    @Test
    fun `symbolFillsRow can detect when a symbol is present in every field of a given row`() {
        // Arrange
        val board = Board(
            """
            XXX
            OXO
            OXX
            """.trimIndent()
        )

        // Act
        val symbolFillsRow = board.symbolFillsRow('X', rowIndex = 0)

        // Assert
        assertTrue(symbolFillsRow)
    }

    @Test
    fun `symbolFillsRow can detect when a symbol is not present in every field of a given row`() {
        // Arrange
        val board = Board(
            """
            XOX
            OXO
            OXX
            """.trimIndent()
        )

        // Act
        val symbolFillsRow = board.symbolFillsRow('X', rowIndex = 0)

        // Assert
        assertFalse(symbolFillsRow)
    }

    @Test
    fun `symbolFillsAColumn can detect when a symbol is not present in every field of any column`() {
        // Arrange
        val board = Board(
            """
            XOX
            OXO
            OXX
            """.trimIndent()
        )

        // Act
        val symbolFillsAColumn = board.symbolFillsAColumn('X')

        // Assert
        assertFalse(symbolFillsAColumn)
    }

    @Test
    fun `symbolFillsAColumn can detect when a symbol is present in every field of the second column`() {
        // Arrange
        val board = Board(
            """
            XXX
            OXO
            OXX
            """.trimIndent()
        )

        // Act
        val symbolFillsAColumn = board.symbolFillsAColumn('X')

        // Assert
        assertTrue(symbolFillsAColumn)
    }

    @Test
    fun `symbolFillsAColumn can detect when a symbol is present in every field of the first column`() {
        // Arrange
        val board = Board(
            """
            XXX
            XOO
            XXX
            """.trimIndent()
        )

        // Act
        val symbolFillsAColumn1 = board.symbolFillsAColumn('X')

        // Assert
        assertTrue(symbolFillsAColumn1)
    }

    @Test
    fun `symbolFillsARow can detect when a symbol is not present in every field of any row`() {
        // Arrange
        val board = Board(
            """
            XOX
            XOO
            XOO
            """.trimIndent()
        )

        // Act
        val symbolFillsARow1 = board.symbolFillsARow('X')

        // Assert
        assertFalse(symbolFillsARow1)
    }

    @Test
    fun `symbolFillsARow can detect when a symbol is present in every field of the second row`() {
        // Arrange
        val board = Board(
            """
            XOX
            XXX
            XOO
            """.trimIndent()
        )

        // Act
        val symbolFillsARow1 = board.symbolFillsARow('X')

        // Assert
        assertTrue(symbolFillsARow1)
    }

    @Test
    fun `symbolFillsARow can detect when a symbol is present in every field of the first row`() {
        // Arrange
        val board = Board(
            """
            XXX
            XOX
            XOO
            """.trimIndent()
        )

        // Act
        val symbolFillsARow1 = board.symbolFillsARow('X')

        // Assert
        assertTrue(symbolFillsARow1)
    }

    @Test
    fun `symbolFillsColumn can detect when a symbol is present in every field of a given column`() {
        // Arrange
        val board = Board(
            """
            XOX
            XOX
            XXO
            """.trimIndent()
        )

        // Act
        val symbolFillsColumn = board.symbolFillsColumn('X', columnIndex = 0)

        // Assert
        assertTrue(symbolFillsColumn)
    }

    @Test
    fun `symbolFillsColumn can detect when a symbol is not present in every field of a given column`() {
        // Arrange
        val board = Board(
            """
            XOX
            OOX
            XXO
            """.trimIndent()
        )

        // Act
        val symbolFillsColumn = board.symbolFillsColumn('X', columnIndex = 0)

        // Assert
        assertFalse(symbolFillsColumn)
    }

    @Test
    fun `symbolFillsFirstDiagonal can detect when a symbol is present in every field of a the first diagonal`() {
        // Arrange
        val board = Board(
            """
            XOX
            OXX
            OOX
            """.trimIndent()
        )

        // Act
        val symbolFillsFirstDiagonal = board.symbolFillsFirstDiagonal('X')

        // Assert
        assertTrue(symbolFillsFirstDiagonal)
    }

    @Test
    fun `symbolFillsFirstDiagonal can detect when a symbol is not present in every field of the first diagonal`() {
        // Arrange
        val board = Board(
            """
            XOX
            OOX
            OOX
            """.trimIndent()
        )

        // Act
        val symbolFillsFirstDiagonal = board.symbolFillsFirstDiagonal('X')

        // Assert
        assertFalse(symbolFillsFirstDiagonal)
    }

    @Test
    fun `symbolFillsSecondDiagonal can detect when a symbol is present in every field of the second diagonal`() {
        // Arrange
        val board = Board(
            """
            XOO
            OOX
            OOX
            """.trimIndent()
        )

        // Act
        val symbolFillsSecondDiagonal = board.symbolFillsSecondDiagonal('O')

        // Assert
        assertTrue(symbolFillsSecondDiagonal)
    }

    @Test
    fun `symbolFillsSecondDiagonal can detect when a symbol is not present in every field of the second diagonal`() {
        // Arrange
        val board = Board(
            """
            XOO
            OXX
            OOX
            """.trimIndent()
        )

        // Act
        val symbolFillsSecondDiagonal = board.symbolFillsSecondDiagonal('O')

        // Assert
        assertFalse(symbolFillsSecondDiagonal)
    }

    @Test
    fun `emptyPositions returns all fields of the board when the board is empty`() {
        // Arrange
        val board = Board(
            """
            ...
            ...
            ...
            """.trimIndent()
        )

        // Act
        val emptyPositions = board.emptyFields

        // Assert
        assertEquals(
            listOf(
                Pair(0, 0), Pair(0, 1), Pair(0, 2),
                Pair(1, 0), Pair(1, 1), Pair(1, 2),
                Pair(2, 0), Pair(2, 1), Pair(2, 2)
            ), emptyPositions
        )
    }

    @Test
    fun `emptyPositions returns an empty list board when the board is full`() {
        // Arrange
        val board = Board(
            """
            XXX
            XXX
            XXX
            """.trimIndent()
        )

        // Act
        val emptyPositions = board.emptyFields

        // Assert
        assertEquals(emptyList<Position>(), emptyPositions)
    }

    @Test
    fun `emptyPositions returns all empty fields of the board`() {
        // Arrange
        val board = Board(
            """
            X..
            .O.
            .OX
            """.trimIndent()
        )

        // Act
        val emptyPositions = board.emptyFields

        // Assert
        assertEquals(
            listOf(
                Pair(0, 1), Pair(0, 2),
                Pair(1, 0), Pair(1, 2),
                Pair(2, 0)
            ), emptyPositions
        )
    }
}
