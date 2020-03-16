package be.harm.deweirdt.tictactoe

import be.harm.deweirdt.domain.TicTacToeController
import be.harm.deweirdt.tictactoe.game.GameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TicTacToeController() }

    viewModel { GameViewModel(get()) }
}
