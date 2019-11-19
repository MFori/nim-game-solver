package cz.martinforejt.nim.app

import cz.martinforejt.nim.core.NimMove
import cz.martinforejt.nim.core.NimSolver

/**
 * Nim game
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
class NimGame(
    private var state: IntArray,
    private var player: Int,
    private val solver: NimSolver,
    private val type: GameType
) {

    /**
     * Start the game
     */
    fun start() {
        printPrintStart()
        makePrediction()
        println()

        while (!isEnd(state)) {
            printState()
            val move = makeMove()
            printMove(move)
            nextPlayer()
        }

        printState()
        println()
        nextPlayer()
        printEnd()
    }

    /**
     * Make move
     * depending on player find best move or read user input
     *
     * @return
     */
    private fun makeMove(): NimMove {
        val move = if (type == GameType.ROBOT_V_ROBOT || (player == 1 && type == GameType.ROBOT_V_HUMAN)) {
            robotMove()
        } else {
            humanMove()
        }

        state[move.pile] -= move.remove
        return move
    }

    /**
     * Find best move using [solver]
     *
     * @return
     */
    private fun robotMove(): NimMove {
        return solver.bestMove(state, player)
    }

    /**
     * Read move from user input
     *
     * @return
     */
    private fun humanMove(): NimMove {
        print("Select pile (1-${state.size}): ")
        val pile = readNumberFromConsole()
        if (pile == null || pile < 1 || pile > state.size || state[pile - 1] == 0) return humanMove()

        print("How many remove (1-${state[pile - 1]}): ")
        val remove = readNumberFromConsole()
        if (remove == null || remove < 1 || remove > state[pile - 1]) return humanMove()

        return NimMove(pile - 1, remove)
    }

    /**
     * Make prediction for winner from current state and player
     */
    private fun makePrediction() {
        val predictedWinner = solver.predictWinner(state, player)
        println("Predicted winner is: " + predictedWinner.playerStr())
    }

    /**
     * Print start information
     */
    private fun printPrintStart() {
        println("Game started. Players are: " + 1.playerStr() + " and " + (-1).playerStr())
        println("Starting player is: " + player.playerStr())
        if (type != GameType.HUMAN_V_HUMAN) {
            println("Selected algorithm is " + solver.javaClass.simpleName)
        }
        printState()
    }

    /**
     * Print current state
     */
    private fun printState() {
        print("Current state is: ")
        for (i in 0 until state.size) {
            print(state[i].toString() + " ")
        }
        println()
    }

    /**
     * Print move
     *
     * @param nimMove
     */
    private fun printMove(nimMove: NimMove) {
        println(player.playerStr() + " removes " + nimMove.remove + " from pile " + (nimMove.pile + 1))
    }

    /**
     * Converts player mark to string name
     *
     * @return string name
     */
    private fun Int.playerStr(): String {
        return if (this == 1) {
            "Hráč 1" + if (type == GameType.ROBOT_V_HUMAN) (" (robot)") else ""
        } else {
            "Hráč 2"
        }
    }

    /**
     * Switch to next player
     */
    private fun nextPlayer() {
        player *= -1
    }

    /**
     * Prints end and winner
     */
    private fun printEnd() {
        println("GAME OVER")
        println("The winner is " + player.playerStr())
    }

    /**
     * Check for end = all piles are empty
     *
     * @param state
     * @return end?
     */
    private fun isEnd(state: IntArray): Boolean {
        return state.sum() == 0
    }

    /**
     * Read one number from console
     * else return null on error
     *
     * @return
     */
    private fun readNumberFromConsole(): Int? {
        return try {
            readLine()?.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}