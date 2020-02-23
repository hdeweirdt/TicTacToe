package be.harm.deweirdt.domain.game

import kotlin.math.floor
import kotlin.math.sqrt

const val DEFAULT_DIMENSION = 3

class Board {
    val dimension: Int

    val fields: Array<Array<Field>>

    constructor(dimension: Int = DEFAULT_DIMENSION) {
        this.dimension = dimension
        fields = Array(dimension) { Array(dimension) { Field() } }
    }

    /**
     * Uses a String representation of a Board to create a Board object.
     * String has to be a matrix-like representation of the Board, e.g.
     * "X0X
     *  ..X
     *  OXO"
     */
    constructor(boardString: String) {
        val stringWithoutNewLines = boardString.replace("\n", "")
        val tempDimension = sqrt(stringWithoutNewLines.length.toDouble())
        assert(tempDimension == floor(tempDimension)) { "Invalid number of elements in board representation" }
        dimension = tempDimension.toInt()
        fields = Array(dimension) { Array(dimension) { Field() } }
        var nextFieldIndex = 0
        for (rowIndex in 0 until dimension) {
            for (columnIndex in 0 until dimension) {
                fields[rowIndex][columnIndex].symbol = stringWithoutNewLines[nextFieldIndex++]
            }
        }
    }

    val isFull: Boolean
        get() {
            for (rowIndex in 0 until dimension) {
                for (columnIndex in 0 until dimension) {
                    if (fields[rowIndex][columnIndex].isEmpty()) {
                        return false
                    }
                }
            }
            return true
        }

    operator fun get(row: Int, column: Int): Field {
        return fields[row][column]
    }

    /**
     * Place the given [symbol] on the designated place on the board.
     *
     * @throws IllegalArgumentException when the location already has a non-empty symbol.
     */
    fun placeSymbol(symbol: Char, row: Int, column: Int) {
        fields[row][column].symbol = symbol
    }

    fun symbolFillsRow(symbol: Char, rowIndex: Int): Boolean {
        return fields[rowIndex].all { it.symbol == symbol }
    }

    fun symbolFillsARow(symbol: Char): Boolean {
        return (0 until dimension).any { rowIndex ->
            symbolFillsRow(
                symbol,
                rowIndex
            )
        }
    }

    fun symbolFillsColumn(symbol: Char, columnIndex: Int): Boolean {
        for (i in 0 until dimension) {
            if (fields[i][columnIndex].symbol != symbol) {
                return false
            }
        }
        return true
    }

    fun symbolFillsAColumn(symbol: Char): Boolean {
        return (0 until dimension).any { columnIndex ->
            symbolFillsColumn(
                symbol,
                columnIndex
            )
        }
    }

    fun symbolFillsFirstDiagonal(symbol: Char): Boolean {
        for (i in 0 until dimension) {
            if (fields[i][i].symbol != symbol) {
                return false
            }
        }
        return true
    }

    fun symbolFillsSecondDiagonal(symbol: Char): Boolean {
        for (i in 0 until dimension) {
            if (fields[i][dimension - 1 - i].symbol != symbol) {
                return false
            }
        }
        return true
    }

    fun symbolFilsADiagonal(symbol: Char): Boolean {
        return symbolFillsFirstDiagonal(symbol) || symbolFillsSecondDiagonal(symbol)
    }

    val emptyFields: List<Position>
        get() {
            val emptyPositions = mutableListOf<Position>()
            for (rowIndex in 0 until dimension) {
                for (columnIndex in 0 until dimension) {
                    if (fields[rowIndex][columnIndex].isEmpty()) {
                        emptyPositions.add(Pair(rowIndex, columnIndex))
                    }
                }
            }
            return emptyPositions
        }

    override fun toString(): String {
        val builder =
            StringBuilder(dimension * dimension + dimension)
        for (rowIndex in 0 until dimension) {
            for (columnIndex in 0 until dimension) {
                builder.append(fields[rowIndex][columnIndex].symbol)
            }
            builder.append('\n')
        }
        return builder.trim().toString()
    }
}
