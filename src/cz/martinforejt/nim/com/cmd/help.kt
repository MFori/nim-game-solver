package cz.martinforejt.nim.com.cmd

/**
 * Created by Martin Forejt on 19.11.2019.
 * me@martinforejt.cz
 *
 * @author Martin Forejt
 */

/**
 * Prints help for application
 */
fun printHelp() {
    println("*********************************************************************")
    println("* Options:                                                          *")
    println("* -type defines execution type:                                     *")
    println("*          -type 1 => robot vs robot (default)                      *")
    println("*          -type 2 => robot vs human                                *")
    println("*          -type 3 => human vs human                                *")
    println("* -state defines starting piles state:                              *")
    println("*          -type 3,4,5 => 3 piles with 3,4 and 5 stones (default)   *")
    println("* -alg defines nim solver algorithm:                                *")
    println("*          -alg 1 => combinatorial algorithm (default)              *")
    println("*          -alg 2 => minimax algorithm (default)                    *")
    println("* -start defines starting player (default random                    *")
    println("*          -start 1 => first play 'player1'                         *")
    println("*          -start -1 => first play 'player2'                        *")
    println("* -help prints this help page                                       *")
    println("*********************************************************************")
}