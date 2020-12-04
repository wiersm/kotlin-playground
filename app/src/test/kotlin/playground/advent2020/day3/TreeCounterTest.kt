package playground.advent2020.day3

import playground.utilities.LogLevel
import playground.utilities.Logger
import kotlin.test.Test
import kotlin.test.assertEquals

class TreeCounterTest {
    val treePattern = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#")

    @Test fun `should count trees on trajectory`() {
        val nrOfTrees = TreeCounter(Logger(LogLevel.DEBUG)).countTreesOnTrajectory(5, 1, treePattern)

        assertEquals(3, nrOfTrees)
    }

    @Test fun `test count and multiply trees on trajectory`() {
        val nrOfTrees1 = TreeCounter().countTreesOnTrajectory(1, 1, treePattern)
        val nrOfTrees2 = TreeCounter().countTreesOnTrajectory(3, 1, treePattern)
        val nrOfTrees3 = TreeCounter().countTreesOnTrajectory(5, 1, treePattern)
        val nrOfTrees4 = TreeCounter().countTreesOnTrajectory(7, 1, treePattern)
        val nrOfTrees5 = TreeCounter().countTreesOnTrajectory(1, 2, treePattern)

        Logger(LogLevel.DEBUG).debug("$nrOfTrees1, $nrOfTrees2, $nrOfTrees3, $nrOfTrees4, $nrOfTrees5")

        val result = nrOfTrees1 * nrOfTrees2 * nrOfTrees3 * nrOfTrees4 * nrOfTrees5

        assertEquals(336, result)
    }
}
