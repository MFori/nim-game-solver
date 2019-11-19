package cz.martinforejt.nim.core

/**
 * Move representation, take [remove] stones from [pile]
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
data class NimMove(
    val pile: Int,
    val remove: Int
)