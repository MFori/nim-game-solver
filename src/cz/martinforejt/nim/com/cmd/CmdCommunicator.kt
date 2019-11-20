package cz.martinforejt.nim.com.cmd

import cz.martinforejt.nim.com.Communicator
import cz.martinforejt.nim.core.NimMove
import cz.martinforejt.nim.data.Configuration
import cz.martinforejt.nim.game.GameType

/**
 * Created by Martin Forejt on 20.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
class CmdCommunicator : Communicator {

    private var type: GameType? = null

    /**
     * Notify user that game started and show game status and configuration
     *
     * @param player current player 1/-1
     * @param state current state
     * @param solver solver name/null
     */
    override fun onGameStart(player: Int, state: IntArray, solver: String?) {
        println("Game started. Players are: " + 1.playerStr() + " and " + (-1).playerStr())
        println("Starting player is: " + player.playerStr())
        if (type != GameType.HUMAN_V_HUMAN) {
            println("Selected algorithm is $solver")
        }
        showCurrentState(state)
    }

    /**
     * Show prediction
     *
     * @param predictedWinner
     */
    override fun showPrediction(predictedWinner: Int) {
        println("Predicted winner is: " + predictedWinner.playerStr())
    }

    /**
     * Print current state
     *
     * @param state
     */
    override fun showCurrentState(state: IntArray) {
        print("Current state is: ")
        for (i in 0 until state.size) {
            print(state[i].toString() + " ")
        }
        println()
    }

    /**
     * Show move
     *
     * @param player current player
     * @param move
     */
    override fun showMove(player: Int, move: NimMove) {
        println(player.playerStr() + " removes " + move.remove + " from pile " + (move.pile + 1))
    }

    /**
     * Notify end
     *
     * @param player winner
     */
    override fun onEnd(player: Int) {
        println("GAME OVER")
        println("The winner is " + player.playerStr())
    }

    /**
     * Read move from player
     *
     * @param state
     * @return
     */
    override fun readMove(state: IntArray): NimMove {
        print("Select pile (1-${state.size}): ")
        val pile = readNumberFromConsole()
        if (pile == null || pile < 1 || pile > state.size || state[pile - 1] == 0) {
            println("Illegal input!")
            return readMove(state)
        }

        print("How many remove (1-${state[pile - 1]}): ")
        val remove = readNumberFromConsole()
        if (remove == null || remove < 1 || remove > state[pile - 1]) {
            println("Illegal input!")
            return readMove(state)
        }

        return NimMove(pile - 1, remove)
    }

    /**
     * Read one number from console
     * else return null on error
     *
     * @return
     */
    private fun readNumberFromConsole(): Int? {
        return try {
            readLine()?.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }

    /**
     * Converts player mark to string name
     *
     * @return string name
     */
    private fun Int.playerStr(): String {
        return if (this == 1) {
            "Player 1" + if (type == GameType.ROBOT_V_HUMAN) (" (robot)") else ""
        } else {
            "Player 2"
        }
    }

    /**
     * Show help
     */
    override fun showHelp() {
        printHelp()
    }

    /**
     * Parse cmd arguments
     *
     * @param args
     */
    fun parseCmdLine(args: Array<String>): Configuration {
        val conf = CmdParser.parseCmdLine(args)
        this.type = conf.type
        return conf
    }
}