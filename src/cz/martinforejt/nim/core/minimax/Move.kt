package cz.martinforejt.nim.core.minimax

/**
 * Move holder used in minimax
 * @see Minimax
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
data class Move(
    val value: Int,
    val action: State
)