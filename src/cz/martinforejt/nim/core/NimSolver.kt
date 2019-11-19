package cz.martinforejt.nim.core

/**
 * Nim solver
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
interface NimSolver {

    /**
     * Find best move for player at current game state
     *
     * @param state - array of stones for each pile
     * @param player - 1/-1
     * @return best move
     */
    fun bestMove(state: IntArray, player: Int): NimMove

    /**
     * Predict winner at current state
     *
     * @param state - array of stone for each pile
     * @param player - 1/-1
     * @return predicted winner 1/-1
     */
    fun predictWinner(state: IntArray, player: Int): Int

}