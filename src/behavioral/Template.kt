package behavioral

fun main() {
    Chess(2).run()
}

class Chess(n: Int): Game(n) {
    private val maxTurns = 10
    private var turn = 1
    override fun getWinningPlayer(): Int {
        return 0
    }

    override fun takeTurn() {
        println("Turn ${turn++} taken by player $currentPlayer")
        currentPlayer = (currentPlayer + 1) % noOfPlayers
    }

    override fun haveWinner(): Boolean {
        return turn == maxTurns
    }

    override fun start() {
        println("Start chess game")
    }

}

abstract class Game(n: Int) {
    protected var currentPlayer: Int = 0
    protected val noOfPlayers = n

    protected abstract fun getWinningPlayer(): Int
    protected abstract fun takeTurn()
    protected abstract fun haveWinner(): Boolean
    protected abstract fun start()

    fun run() {
        start()
        while (!haveWinner())
            takeTurn()
        println("Player ${getWinningPlayer()} wins")
    }
}