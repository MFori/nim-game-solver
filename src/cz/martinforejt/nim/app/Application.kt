package cz.martinforejt.nim.app

import cz.martinforejt.nim.com.cmd.CmdCommunicator
import cz.martinforejt.nim.core.NimSolver
import cz.martinforejt.nim.core.comb.CombNimSolver
import cz.martinforejt.nim.core.minimax.MinimaxNimSolver
import cz.martinforejt.nim.game.NimGame

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
    val communicator = CmdCommunicator()
    val configuration = communicator.parseCmdLine(args)

    if (configuration.help) {
        communicator.showHelp()
    }

    val solver = createSolver(configuration.alg)
    val game = NimGame(
        state = configuration.state,
        player = configuration.player,
        solver = solver,
        type = configuration.type,
        communicator = communicator
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