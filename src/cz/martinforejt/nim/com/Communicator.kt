package cz.martinforejt.nim.com

import cz.martinforejt.nim.core.NimMove

/**
 * Created by Martin Forejt on 20.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */
interface Communicator {

    /**
     * Notify user that game started and show game status and configuration
     *
     * @param player current player 1/-1
     * @param state current state
     * @param solver solver name/null
     */
    fun onGameStart(player: Int, state: IntArray, solver: String?)

    /**
     * Show prediction
     *
     * @param predictedWinner
     */
    fun showPrediction(predictedWinner: Int)

    /**
     * Show current state
     *
     * @param state
     */
    fun showCurrentState(state: IntArray)

    /**
     * Show move
     *
     * @param player current player
     * @param move
     */
    fun showMove(player: Int, move: NimMove)

    /**
     * Read move from player
     *
     * @param state
     * @return
     */
    fun readMove(state: IntArray): NimMove

    /**
     * Notify end
     *
     * @param player winner
     */
    fun onEnd(player: Int)

    /**
     * Show help
     */
    fun showHelp()
}