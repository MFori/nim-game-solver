package cz.martinforejt.nim.app

import cz.martinforejt.nim.core.NimSolver
import cz.martinforejt.nim.core.comb.CombNimSolver
import cz.martinforejt.nim.core.minimax.MinimaxNimSolver

/**
 * Start point of application
 * Run with -help to see options
 *
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
fun main(args: Array<String>) {
    val cmdArgs = CmdParser.parseCmdLine(args)
    if (cmdArgs.help) printHelp()
    val solver = createSolver(cmdArgs.alg)

    val game = NimGame(
        state = cmdArgs.state,
        player = cmdArgs.player,
        solver = solver,
        type = cmdArgs.type
    )
    game.start()
}

/**
 * Create solver depending on solver type:
 * 1 => [CombNimSolver]
 * 2 => [MinimaxNimSolver]
 *
 * @param solverType
 * @return
 */
private fun createSolver(solverType: Int): NimSolver {
    return if (solverType == 1) {
        CombNimSolver()
    } else {
        MinimaxNimSolver()
    }
}