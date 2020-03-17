package be.harm.deweirdt.domain

import be.harm.deweirdt.domain.AI.AIPlayer
import be.harm.deweirdt.domain.AI.AdjustableDifficultyAI
import be.harm.deweirdt.domain.AI.BoardEvaluator
import be.harm.deweirdt.domain.AI.DifficultyToDepthMapper
import be.harm.deweirdt.domain.game.Board
import be.harm.deweirdt.domain.game.Game
import org.koin.dsl.module

val domainModule = module {
    single { BoardEvaluator() }
    single { DifficultyToDepthMapper() }
    factory { Board() }
    factory { Game() }

    factory { (game: Game, difficulty: Difficulty) ->
        AdjustableDifficultyAI(game, difficulty) as AIPlayer
    }
}
