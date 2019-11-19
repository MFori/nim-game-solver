package cz.martinforejt.nim.core.comb

import cz.martinforejt.nim.core.NimMove
import kotlin.random.Random

/**
 * Utility functions for solving nim with combinatorial
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
object CombUtils {

    /**
     * Calculate Nim-Sum (Proof of the winning formula)
     * https://en.wikipedia.org/wiki/Nim#Proof_of_the_winning_formula
     *
     * @param state
     * @return
     */
    fun nimSum(state: IntArray): Int {
        var nimSum = state[0]
        for (i in 1 until state.size) {
            nimSum = nimSum.xor(state[i])
        }
        return nimSum
    }

    /**
     * Find best move
     * If nimSum for state != 0, current player will win, so find [realBestMove]
     * else find [randomMove]
     *
     * @param state
     * @return
     */
    fun bestMove(state: IntArray): NimMove {
        val nimSum = nimSum(state)
        return if (nimSum != 0) {
            realBestMove(state, nimSum)
        } else {
            randomMove(state)
        }
    }

    /**
     * Find best move
     *
     * @param state
     * @param nimSum
     * @return
     */
    private fun realBestMove(state: IntArray, nimSum: Int): NimMove {
        for (i in 0 until state.size) {
            if ((state[i].xor(nimSum)) < state[i]) {
                return NimMove(
                    pile = i,
                    remove = state[i] - (state[i].xor(nimSum))
                )
            }
        }

        return NimMove(0, 0)
    }

    /**
     * Do random move
     *
     * @param state
     * @return
     */
    private fun randomMove(state: IntArray): NimMove {
        val availablePiles = List(state.size) { i -> i }.shuffled().toIntArray()

        for (pile in availablePiles) {
            if (state[pile] != 0) {
                return NimMove(
                    pile = pile,
                    remove = Random.nextInt(1, state[pile] + 1)
                )
            }
        }

        return NimMove(0, 0)
    }
}