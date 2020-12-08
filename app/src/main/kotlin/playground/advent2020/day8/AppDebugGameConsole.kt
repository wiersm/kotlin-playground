package playground.advent2020.day8

import playground.utilities.*

fun main() {
    val l = Logger(LogLevel.INFO)
    val instructions = InstructionParser(l).parse(FileReader().readLines(resource("/input-advent2020-day8.txt")))
    val resultAtInfiniteLoop = InstructionRunner(l).run(instructions)
    UI().printProgramResult("Accumulator value at start of infinite loop: $resultAtInfiniteLoop")

    val resultAfterFix = InstructionRunner(l).fixAndRun(instructions)
    UI().printProgramResult("Accumulator value with fix: $resultAfterFix")
}
