package cz.martinforejt.nim.app

import kotlin.random.Random

/**
 * Utility for parsing cmd arguments
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
object CmdParser {

    /**
     * Parse cmd arguments to [CmdArgs] (filled with default values)
     *
     * @param args
     * @return
     */
    fun parseCmdLine(args: Array<String>): CmdArgs {
        var cmdArgs = CmdArgs()

        var i = 0
        var arg: String

        while (i < args.size && args[i].startsWith("-")) {
            arg = args[i++]
            if (arg == "-type") {
                if (i < args.size) {
                    try {
                        cmdArgs = cmdArgs.copy(type = GameType.values()[args[i++].toInt() - 1])
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad type argument. Try -help to see options.")
                    } catch (e2: IndexOutOfBoundsException) {
                        throw IllegalArgumentException("Bad type argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-type requires type. Try -help to see options.")
            } else if (arg == "-state") {
                if (i < args.size) {
                    try {
                        cmdArgs = cmdArgs.copy(state = args[i++].split(",").map { it.toInt() }.toIntArray())
                    } catch (e: java.lang.NumberFormatException) {
                        throw IllegalArgumentException("Bad state argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-state requires state. Try -help to see options.")
            } else if (arg == "-help") {
                cmdArgs = cmdArgs.copy(help = true)
            } else if (arg == "-alg") {
                if (i < args.size) {
                    try {
                        cmdArgs = cmdArgs.copy(alg = args[i++].toInt())
                        if (cmdArgs.alg <= 0 || cmdArgs.alg >= 3) throw IllegalArgumentException("Bad algorithm argument. Try -help to see options.")
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad algorithm type argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-alg requires algorithm type. Try -help to see options.")
            } else if (arg == "-start") {
                if (i < args.size) {
                    try {
                        cmdArgs = cmdArgs.copy(player = args[i++].toInt())
                        if (cmdArgs.player != 1 && cmdArgs.player != -1) throw IllegalArgumentException("Bad start player argument. Try -help to see options.")
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad start player argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-start requires player. Try -help to see options.")
            } else {
                throw IllegalArgumentException("Bad arguments. Try -help to see options.")
            }
        }

        return cmdArgs
    }

    /**
     * Cmd arguments holder
     *
     * @property type
     * @property state
     * @property help
     * @property alg
     */
    @Suppress("ArrayInDataClass")
    data class CmdArgs(
        val type: GameType = GameType.ROBOT_V_ROBOT,
        val state: IntArray = intArrayOf(3, 4, 5),
        val help: Boolean = false,
        val alg: Int = 1,
        val player: Int = if (Random.nextInt() > 0) 1 else -1
    )
}