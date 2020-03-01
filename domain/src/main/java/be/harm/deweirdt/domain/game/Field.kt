package be.harm.deweirdt.domain.game

const val EMPTY_SYMBOL = '.'
const val SYMBOL_PLAYER_X = 'X'
const val SYMBOL_PLAYER_O = 'O'

data class Field(val symbol: Char = EMPTY_SYMBOL) {
    fun isEmpty(): Boolean {
        return symbol == EMPTY_SYMBOL
    }
}
