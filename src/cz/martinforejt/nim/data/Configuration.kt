package cz.martinforejt.nim.data

import cz.martinforejt.nim.game.GameType
import kotlin.random.Random

/**
 * Game configuration holder, represents data base
 *
 * Created by Martin Forejt on 20.11.2019.
 * me@martinforejt.cz

 * @author Martin Forejt

 * @property type
 * @property state
 * @property help
 * @property alg
 * @property player
 */
@Suppress("ArrayInDataClass")
data class Configuration(
    val type: GameType = GameType.BEST_MOVE,
    val state: IntArray? = null,
    val help: Boolean = false,
    val alg: Int = 1,
    val player: Int = if (Random.nextInt() > 0) 1 else -1
)