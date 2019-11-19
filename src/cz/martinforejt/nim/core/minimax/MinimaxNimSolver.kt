package cz.martinforejt.nim.core.minimax

import cz.martinforejt.nim.core.NimMove
import cz.martinforejt.nim.core.NimSolver

/**
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
class MinimaxNimSolver : NimSolver {

    /**
     * Find best move for player at current game state
     *
     * @param state - array of stones for each pile
     * @param player - 1/-1
     * @return best move
     */
    override fun bestMove(state: IntArray, player: Int): NimMove {
        return Minimax.minimax(State(state, player)).action.toNimMove(state)
    }

    /**
     * Predict winner at current state
     *
     * @param state - array of stone for each pile
     * @param player - 1/-1
     * @return predicted winner 1/-1
     */
    override fun predictWinner(state: IntArray, player: Int): Int {
        return Minimax.minimax(State(state, player)).value
    }

    /**
     * Convert state using in minimax algorithm to [NimMove]
     *
     * @param startState
     * @return
     */
    private fun State.toNimMove(startState: IntArray): NimMove {
        for (i in 0 until this.state.size) {
            if (this.state[i] != startState[i]) {
                return NimMove(
                    pile = i,
                    remove = startState[i] - this.state[i]
                )
            }
        }

        return NimMove(0, 0)
    }
}