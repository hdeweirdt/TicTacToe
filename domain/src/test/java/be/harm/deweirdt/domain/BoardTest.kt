package be.harm.deweirdt.domain

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BoardTest {
    private lateinit var board: Board

    @Before
    fun setUp() {
        board = Board()
    }

    @Test
    fun `the board consist of 3 by 3 fields`() {
        assertEquals(3, board.fields.size)

        assertEquals(3, board.fields[0].size)
        assertEquals(3, board.fields[1].size)
        assertEquals(3, board.fields[2].size)
    }

    @Test
    fun `a board is considered full when all fields are not empty`() {
        // Arrange
        for (rowIndex in 0 until DIMENSION) {
            for (columnIndex in 0 until DIMENSION) {
                board.placeSymbol('X', rowIndex, columnIndex)
            }
        }

        // Act
        val isFull = board.isFull

        // Assert
        assertTrue(isFull)
    }

    @Test
    fun `a board is not full as soon as one field is not empty`() {
        // Arrange
        board.placeSymbol('X', 0, 0)

        // Act
        val isFull = board.isFull

        // Assert
        assertFalse(isFull)
    }

    @Test
    fun `an empty board is considered not full`() {
        assertFalse(board.isFull)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `placing a symbol on top of another symbol results in an error`() {
        // Arrange
        board.placeSymbol('X', 0, 0)

        // Act
        board.placeSymbol('O', 0, 0)
    }

    @Test
    fun `symbolFillsRow can detect when a symbol is present in every field of a given row`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 0, 1)
        board.placeSymbol('X', 0, 2)

        // Act
        val symbolFillsRow = board.symbolFillsRow('X', rowIndex = 0)

        // Assert
        assertTrue(symbolFillsRow)
    }

    @Test
    fun `symbolFillsRow can detect when a symbol is not present in every field of a given row`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 0, 2)

        // Act
        val symbolFillsRow = board.symbolFillsRow('X', rowIndex = 0)

        // Assert
        assertFalse(symbolFillsRow)
    }

    @Test
    fun `symbolFillsColumn can detect when a symbol is present in every field of a given column`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 1, 0)
        board.placeSymbol('X', 2, 0)

        // Act
        val symbolFillsColumn = board.symbolFillsColumn('X', columnIndex = 0)

        // Assert
        assertTrue(symbolFillsColumn)
    }

    @Test
    fun `symbolFillsColumn can detect when a symbol is not present in every field of a given column`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 0, 2)

        // Act
        val symbolFillsColumn = board.symbolFillsColumn('X', columnIndex = 0)

        // Assert
        assertFalse(symbolFillsColumn)
    }

    @Test
    fun `symbolFillsFirstDiagonal can detect when a symbol is present in every field of a the first diagonal`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 1, 1)
        board.placeSymbol('X', 2, 2)

        // Act
        val symbolFillsFirstDiagonal = board.symbolFillsFirstDiagonal('X')

        // Assert
        assertTrue(symbolFillsFirstDiagonal)
    }

    @Test
    fun `symbolFillsFirstDiagonal can detect when a symbol is not present in every field of the first diagonal`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 2, 2)

        // Act
        val symbolFillsFirstDiagonal = board.symbolFillsFirstDiagonal('X')

        // Assert
        assertFalse(symbolFillsFirstDiagonal)
    }

    @Test
    fun `symbolFillsSecondDiagonal can detect when a symbol is present in every field of the second diagonal`() {
        // Arrange
        board.placeSymbol('X', 0, 2)
        board.placeSymbol('X', 1, 1)
        board.placeSymbol('X', 2, 0)

        // Act
        val symbolFillsSecondDiagonal = board.symbolFillsSecondDiagonal('X')

        // Assert
        assertTrue(symbolFillsSecondDiagonal)
    }

    @Test
    fun `symbolFillsSecondDiagonal can detect when a symbol is not present in every field of the second diagonal`() {
        // Arrange
        board.placeSymbol('X', 0, 0)
        board.placeSymbol('X', 0, 2)

        // Act
        val symbolFillsSecondDiagonal = board.symbolFillsSecondDiagonal('X')

        // Assert
        assertFalse(symbolFillsSecondDiagonal)
    }
}
