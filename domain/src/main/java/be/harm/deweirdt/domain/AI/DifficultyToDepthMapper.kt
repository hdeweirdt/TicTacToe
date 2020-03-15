package be.harm.deweirdt.domain.AI

import be.harm.deweirdt.domain.Difficulty

object DifficultyToDepthMapper {
    fun mapToMaxDepth(difficulty: Difficulty): Int {
        return when (difficulty) {
            Difficulty.EASY -> 3
            Difficulty.HARD -> Int.MAX_VALUE
        }
    }
}
