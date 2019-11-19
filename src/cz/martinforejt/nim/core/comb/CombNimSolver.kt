package cz.martinforejt.nim.core.comb

import cz.martinforejt.nim.core.NimMove
import cz.martinforejt.nim.core.NimSolver

/**
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
class CombNimSolver : NimSolver {

    /**
     * Find best move for player at current game state
     *
     * @param state - array of stones for each pile
     * @param player - 1/-1
     * @return best move
     */
    override fun bestMove(state: IntArray, player: Int): NimMove {
        return CombUtils.bestMove(state)
    }

    /**
     * Predict winner at current state
     *
     * @param state - array of stone for each pile
     * @param player - 1/-1
     * @return predicted winner 1/-1
     */
    override fun predictWinner(state: IntArray, player: Int): Int {
        return if (CombUtils.nimSum(state) != 0) {
            player
        } else {
            player * -1
        }
    }
}