package cz.martinforejt.nim.core.minimax

/**
 * Minimax
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
object Minimax {

    /**
     * Cached moves by state
     */
    private val cache = mutableMapOf<State, Move>()

    /**
     * Find best move using minimax
     *
     * @param state
     * @return
     */
    fun minimax(state: State): Move {
        return minimaxRecursive(state)
    }

    /**
     * Find best move using minimax
     *
     * @param state
     * @return
     */
    private fun minimaxRecursive(state: State): Move {
        if (state.isEnd()) {
            return Move(state.utility(), state)
        }
        if (cache.containsKey(state)) {
            cache[state]?.let { return it }
        }

        val choices = mutableListOf<Move>()
        for (action in state.actions()) {
            choices.add(
                Move(
                    value = minimaxRecursive(action).value,
                    action = action
                )
            )
        }

        val res = if (state.player == 1) {
            choices.maxBy { it.value }!!
        } else {
            choices.minBy { it.value }!!
        }
        cache[state] = res
        return res
    }
}