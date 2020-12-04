package playground.advent2020.day3

import playground.utilities.FileReader
import playground.utilities.UI
import playground.utilities.resource

fun main() {
    val treePattern = FileReader().readLines(resource("/input-advent2020-day3.txt")).toList()

    val nrOfTrees2 = TreeCounter().countTreesOnTrajectory(3, 1, treePattern)

    UI().printProgramResult("Number of trees on first trajectory: $nrOfTrees2")

    val nrOfTrees1 = TreeCounter().countTreesOnTrajectory(1, 1, treePattern)
    val nrOfTrees3 = TreeCounter().countTreesOnTrajectory(5, 1, treePattern)
    val nrOfTrees4 = TreeCounter().countTreesOnTrajectory(7, 1, treePattern)
    val nrOfTrees5 = TreeCounter().countTreesOnTrajectory(1, 2, treePattern)

    val result = nrOfTrees1 * nrOfTrees2 * nrOfTrees3 * nrOfTrees4 * nrOfTrees5

    UI().printProgramResult("Trajectories multiplied together: $result")
}
