package cz.martinforejt.nim.game

import cz.martinforejt.nim.com.Communicator
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
    private val type: GameType,
    private val communicator: Communicator
) {

    /**
     * Start the game
     */
    fun start() {
        communicator.onGameStart(player, state, solver.javaClass.simpleName)
        communicator.showPrediction(makePrediction())

        while (!isEnd(state)) {
            communicator.showCurrentState(state)
            val move = makeMove()
            communicator.showMove(player, move)
            nextPlayer()
        }

        communicator.showCurrentState(state)
        nextPlayer()
        communicator.onEnd(player)
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
            communicator.readMove(state)
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
     * Make prediction for winner from current state and player
     */
    private fun makePrediction(): Int {
        return solver.predictWinner(state, player)
    }

    /**
     * Switch to next player
     */
    private fun nextPlayer() {
        player *= -1
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
}