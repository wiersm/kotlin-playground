package playground.advent2020.day17

import playground.utilities.*

fun main() {
    val input = FileReader().readLines(resource("/input-advent2020-day17.txt")).toList()
    val nrOfActiveCubes = HyperCubeSimulator(Logger(LogLevel.NONE)).activeCubesAfterSixCycles(input)
    UI().printProgramResult("Number of active cubes after six iterations: $nrOfActiveCubes")
}
