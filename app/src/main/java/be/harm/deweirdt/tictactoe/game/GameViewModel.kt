package be.harm.deweirdt.tictactoe.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import be.harm.deweirdt.domain.Difficulty
import be.harm.deweirdt.domain.TicTacToeController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(
    private val controller: TicTacToeController
) : ViewModel() {

    private val _currentPlayerName = MutableLiveData<String>()
    val currentPlayerName: LiveData<String> = _currentPlayerName

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

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _fields = MutableLiveData<Array<Array<String>>>()
    val fields: LiveData<Array<Array<String>>> = _fields

    init {
        controller.startNewGame(chosenDifficulty)
        updateUIState()
    }

    fun updateUIState() {
        _fields.value = convertToStrings(controller.getCurrentBoard())
        _gameOver.value = controller.isGameOver
        _isDraw.value = controller.isDraw
        _winningPlayerName.value = controller.getWinningPlayerSymbol()?.toString()
        _currentPlayerName.value = controller.getCurrentPlayerSymbol().toString()
    }

    private fun convertToStrings(board: Array<Array<Char>>): Array<Array<String>> {
        return Array(board.size) { row -> Array(board.size) { column -> board[row][column].toString() } }
    }

    fun positionChosen(rowIndex: Int, columnIndex: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) { controller.humanMove(rowIndex, columnIndex) }
            withContext(Dispatchers.Main) { updateUIState() }
            if (!controller.isGameOver) {
                withContext(Dispatchers.Default) { controller.computerMove() }
                withContext(Dispatchers.Main) { updateUIState() }
            }
        }
    }

    fun onPlayAgainClicked() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) { controller.startNewGame(chosenDifficulty) }
            withContext(Dispatchers.Main) { updateUIState() }
            if (controller.isComputerTurn) {
                withContext(Dispatchers.Default) { controller.computerMove() }
                withContext(Dispatchers.Main) { updateUIState() }
            }
        }
    }

    fun onCheckedHardDifficulty(hardDifficultySelected: Boolean) {
        this.hardDifficultySelected = hardDifficultySelected
        controller.setDifficulty(chosenDifficulty)
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
