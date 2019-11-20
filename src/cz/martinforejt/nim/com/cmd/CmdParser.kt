package cz.martinforejt.nim.com.cmd

import cz.martinforejt.nim.data.Configuration
import cz.martinforejt.nim.game.GameType

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
     * Parse cmd arguments to [Configuration] (filled with default values)
     *
     * @param args
     * @return
     */
    fun parseCmdLine(args: Array<String>): Configuration {
        var config = Configuration()

        var i = 0
        while (i < args.size && args[i].startsWith("-")) {
            val arg = args[i++]
            if (arg == "-type") {
                if (i < args.size) {
                    try {
                        config = config.copy(type = GameType.values()[args[i++].toInt() - 1])
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad type argument. Try -help to see options.")
                    } catch (e2: IndexOutOfBoundsException) {
                        throw IllegalArgumentException("Bad type argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-type requires type. Try -help to see options.")
            } else if (arg == "-state") {
                if (i < args.size) {
                    try {
                        config = config.copy(state = args[i++].split(",").map { it.toInt() }.toIntArray())
                    } catch (e: java.lang.NumberFormatException) {
                        throw IllegalArgumentException("Bad state argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-state requires state. Try -help to see options.")
            } else if (arg == "-help") {
                config = config.copy(help = true)
            } else if (arg == "-alg") {
                if (i < args.size) {
                    try {
                        config = config.copy(alg = args[i++].toInt())
                        if (config.alg <= 0 || config.alg >= 3) throw IllegalArgumentException("Bad algorithm argument. Try -help to see options.")
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad algorithm type argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-alg requires algorithm type. Try -help to see options.")
            } else if (arg == "-start") {
                if (i < args.size) {
                    try {
                        config = config.copy(player = args[i++].toInt())
                        if (config.player != 1 && config.player != -1) throw IllegalArgumentException("Bad start player argument. Try -help to see options.")
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Bad start player argument. Try -help to see options.")
                    }
                } else throw IllegalArgumentException("-start requires player. Try -help to see options.")
            } else {
                throw IllegalArgumentException("Bad arguments. Try -help to see options.")
            }
        }

        return config
    }
}