package playground.advent2020.day3

import playground.utilities.Logger

class TreeCounter(val logger: Logger) {
    fun countTreesOnTrajectory(stepsX: Int, stepsY: Int, treePattern: List<String>) : Long {
        val patternWidth = treePattern[0].length
        var nrOfTrees : Long = 0
        var x = 0
        var y = 0
        while (y < treePattern.size) {
            val xInPattern = x.rem(patternWidth)

            val encounteredTree = treePattern[y][xInPattern] == '#'
            if (encounteredTree) {
                logger.debug("Encountered tree at position $x, $y (pattern position $xInPattern, $y)")
                nrOfTrees++
            } else {
                logger.debug("No tree at position $x, $y (pattern position $xInPattern, $y)")
            }

            x += stepsX
            y += stepsY
        }

        return nrOfTrees
    }

    constructor() : this(Logger())


}
