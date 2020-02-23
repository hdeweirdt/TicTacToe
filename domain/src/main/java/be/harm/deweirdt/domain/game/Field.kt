package be.harm.deweirdt.domain.game

const val EMPTY_SYMBOL = '.'
const val SYMBOL_PLAYER_X = 'X'
const val SYMBOL_PLAYER_O = 'O'

class Field {
    // TODO: force modification to symbol to be only possible by Game
    var symbol: Char = EMPTY_SYMBOL
        set(value) {
            if (!isEmpty()) {
                throw IllegalArgumentException("Replacing symbols is not allowed.")
            }
            field = value
        }

    fun isEmpty(): Boolean {
        return symbol == EMPTY_SYMBOL
    }
}
