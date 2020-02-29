package be.harm.deweirdt.tictactoe.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _currentPlayerName = MutableLiveData<String>()
    val currentPlayerName: LiveData<String> = _currentPlayerName

    private val _winningPlayerName = MutableLiveData<String>()
    val winningPlayerName: LiveData<String> = _winningPlayerName

    val winningPlayerVisible: LiveData<Boolean> = Transformations.map(_winningPlayerName) { name ->
        !name.isNullOrEmpty()
    }

    private val _fields = MutableLiveData<Array<Array<String>>>()
    val fields: LiveData<Array<Array<String>>> = _fields

    init {
        _fields.value = Array(3) { Array(3) { " " } }
        // Init so [winningPlayerVisible] updates
        _winningPlayerName.value = null
        _currentPlayerName.value = "X"
    }

    fun setWinningPlayer(name: String) {
        _winningPlayerName.value = name
    }
}
