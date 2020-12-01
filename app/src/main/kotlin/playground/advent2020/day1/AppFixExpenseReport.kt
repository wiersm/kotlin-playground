package playground.advent2020.day1

import playground.utilities.*

fun main() {
    val logger = Logger(LogLevel.DEBUG)

    // Read the input
    var entries = FileReader(logger).readLines(resource("/input-advent2020-day1.txt")).map(String::toInt)

    // Find the product
    val product = Sum2020Finder(logger).findProduct(entries)

    // Report the result
    UI().printProgramResult("Product is: $product")

    // Read the input again
    entries = FileReader(logger).readLines(resource("/input-advent2020-day1.txt")).map(String::toInt)

    // Find the tri-product
    val triProduct = Sum2020Finder(logger).findTriProduct(entries)

    // Report the result
    UI().printProgramResult("Tri-product is: $triProduct")
}
