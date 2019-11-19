package cz.martinforejt.nim.core.minimax

import java.util.*

/**
 * Represent current state,
 * Each state is represent by state of piles and current player
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
class State(
    val state: IntArray,
    val player: Int
) {

    /**
     * Generate available states under this state
     * And change player for next state -> -1*player
     *
     * @return
     */
    fun actions(): List<State> {
        val actions = mutableListOf<State>()

        for (i in 0 until state.size) {
            for (j in 0 until state[i]) {
                val newState = state.copyOf()
                newState[i] = j
                actions.add(State(newState, -1 * player))
            }
        }

        return actions
    }

    /**
     * The utility is 1 for the player having won
     * and -1 for the player having lost
     *
     * @return
     */
    fun utility(): Int {
        return if (player == 1) {
            -1 //lost
        } else {
            1 // win
        }
    }

    /**
     * Check if game end at this state
     * End is if all piles are empty
     *
     * @return
     */
    fun isEnd(): Boolean {
        return state.sum() == 0
    }


    override fun toString(): String {
        return "State(state=${Arrays.toString(state)}, player=$player)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State

        if (!state.contentEquals(other.state)) return false
        if (player != other.player) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state.contentHashCode()
        result = 31 * result + player
        return result
    }
}