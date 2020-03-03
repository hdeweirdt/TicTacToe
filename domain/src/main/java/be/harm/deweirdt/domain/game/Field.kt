package be.harm.deweirdt.domain.game

const val EMPTY_SYMBOL = '.'

internal data class Field(val symbol: Char = EMPTY_SYMBOL) {
    fun isEmpty(): Boolean {
        return symbol == EMPTY_SYMBOL
    }
}
