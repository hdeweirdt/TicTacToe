import be.harm.deweirdt.domain.AI.HardAIPlayer
import be.harm.deweirdt.domain.game.Game

fun main() {
    val game = Game()

    while (game.isOver.not()) {
        val player = HardAIPlayer(game)
        val nextMove = player.getNextMove()
        game.currentPlayerMove(nextMove)
        println(game.board.toString())
        println()
    }
}