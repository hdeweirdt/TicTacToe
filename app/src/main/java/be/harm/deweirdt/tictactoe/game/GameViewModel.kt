package be.harm.deweirdt.tictactoe.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.harm.deweirdt.domain.Difficulty
import be.harm.deweirdt.domain.TicTacToeController
import be.harm.deweirdt.tictactoe.R
import be.harm.deweirdt.tictactoe.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(
    private val controller: TicTacToeController
) : ViewModel() {

    private val _currentPlayerName = MutableLiveData<String>()
    val currentPlayerName: LiveData<String> = _currentPlayerName

    private val _currentPlayerSymbol = MutableLiveData<String>()
    val currentPlayerSymbol: LiveData<String> = _currentPlayerSymbol

    private val _winningPlayerName = MutableLiveData<String>()
    val winningPlayerName: LiveData<String> = _winningPlayerName

    private val _gameOver = MutableLiveData<Boolean>()
    val gameOver: LiveData<Boolean> = _gameOver

    val winningPlayerVisible: LiveData<Boolean> = Transformations.map(winningPlayerName) { name ->
        !name.isNullOrEmpty()
    }

    var hardDifficultySelected = true

    private val chosenDifficulty: Difficulty
        get() {
            if (hardDifficultySelected) {
                return Difficulty.HARD
            } else {
                return Difficulty.EASY
            }
        }

    private val _isDraw = MutableLiveData<Boolean>()
    val isDraw: LiveData<Boolean> = _isDraw

    private val _errorMessage = SingleLiveEvent<Int>()
    val errorMessage: LiveData<Int> = _errorMessage

    private val _fields = MutableLiveData<Array<Array<String>>>()
    val fields: LiveData<Array<Array<String>>> = _fields

    private val _uiEnabled = MutableLiveData<Boolean>().apply {
        value = true
    }
    val uiEnabled: LiveData<Boolean> = _uiEnabled

    init {
        controller.startNewGame(chosenDifficulty)
        viewModelScope.launch(Dispatchers.Main) { updateUIState() }
    }

    suspend fun updateUIState() {
        withContext(Dispatchers.Main) {
            _fields.value = (convertToStrings(controller.getCurrentBoard()))
            _gameOver.value = controller.isGameOver
            _isDraw.value = controller.isDraw
            _winningPlayerName.value = controller.getWinningPlayerName()
            _currentPlayerName.value = controller.getCurrentPlayerName()
            _currentPlayerSymbol.value = controller.getCurrentPlayerSymbol().toString()
        }
    }

    private fun convertToStrings(board: Array<Array<Char>>): Array<Array<String>> {
        return Array(board.size) { row -> Array(board.size) { column -> board[row][column].toString() } }
    }

    fun positionChosen(rowIndex: Int, columnIndex: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            disableNewUserInput()
            try {
                controller.humanMove(rowIndex, columnIndex)
            } catch (e: IllegalArgumentException) {
                _errorMessage.postValue(R.string.error_invalid_move)
            }
            updateUIState()
            if (!controller.isGameOver) {
                controller.computerMove()
                updateUIState()
            }
            enableNewUserInput()
        }
    }

    private fun enableNewUserInput() {
        _uiEnabled.postValue(true)
    }

    private fun disableNewUserInput() {
        _uiEnabled.postValue(false)
    }

    fun onPlayAgainClicked() {
        viewModelScope.launch(Dispatchers.Default) {
            disableNewUserInput()
            controller.startNewGame(chosenDifficulty)
            updateUIState()
            if (controller.isComputerTurn) {
                controller.computerMove()
                updateUIState()
            }
            enableNewUserInput()
        }
    }

    fun onCheckedHardDifficulty(hardDifficultySelected: Boolean) {
        disableNewUserInput()
        this.hardDifficultySelected = hardDifficultySelected
        controller.setDifficulty(chosenDifficulty)
        enableNewUserInput()
    }

    @Suppress("UNCHECKED_CAST")
    internal class GameViewModelFactory constructor(
        private val controller: TicTacToeController
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
                GameViewModel(controller) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}
