package be.harm.deweirdt.domain

const val DIMENSION = 3

class Board : Iterable<Field> {

    override fun iterator(): Iterator<Field> {
        return BoardIterator()
    }

    val isFull: Boolean
        get() {
            for (field in this) {
                if (field.isEmpty()) {
                    return false
                }
            }
            return true
        }

    // TODO: create in function of DIMENSION
    val fields = arrayOf(
        arrayOf(Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field()),
        arrayOf(Field(), Field(), Field())
    )

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

    inner class BoardIterator : Iterator<Field> {
        private var currentRowIndex = 0
        private var currentColumnIndex = 0

        override fun hasNext(): Boolean {
            return currentRowIndex < DIMENSION - 1 && currentColumnIndex < DIMENSION - 1
        }

        override fun next(): Field {
            return fields[currentRowIndex++][currentColumnIndex++]
        }
    }

    fun symbolFillsRow(symbol: Char, rowIndex: Int): Boolean {
        return fields[rowIndex].all { it.symbol == symbol }
    }

    fun symbolFillsColumn(symbol: Char, columnIndex: Int): Boolean {
        for (i in 0 until DIMENSION) {
            if (fields[i][columnIndex].symbol != symbol) {
                return false
            }
        }
        return true
    }

    fun symbolFillsFirstDiagonal(symbol: Char): Boolean {
        for (i in 0 until DIMENSION) {
            if (fields[i][i].symbol != symbol) {
                return false
            }
        }
        return true
    }

    fun symbolFillsSecondDiagonal(symbol: Char): Boolean {
        for (i in 0 until DIMENSION) {
            if (fields[i][DIMENSION - 1 - i].symbol != symbol) {
                return false
            }
        }
        return true
    }
}
