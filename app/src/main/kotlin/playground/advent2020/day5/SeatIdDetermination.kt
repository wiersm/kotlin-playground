package playground.advent2020.day5

import playground.utilities.Logger
import kotlin.math.max

fun determineSeatId(seatCode: String, l: Logger) : Int {
    // Convert the row code to a binary number and parse it.
    val rowInBinary = seatCode.dropLast(3).replace('F', '0').replace('B', '1')
    val rowId = Integer.parseUnsignedInt(rowInBinary, 2)
    l.debug("Row id: $rowId")

    // Convert the column code to a binary number and parse it.
    val columnInBinary = seatCode.takeLast(3).replace('L', '0').replace('R', '1')
    val columnId = Integer.parseUnsignedInt(columnInBinary, 2)
    l.debug("Column id: $columnId")

    // Return the seat ID
    return rowId * 8 + columnId
}


fun determineMaxSeatId(seatCodes: Sequence<String>, l: Logger) =
    determineSeatIds(seatCodes, l).reduce(operation = ::max)

fun determineSeatIds(seatCodes: Sequence<String>, l: Logger) =
    seatCodes.map { s -> determineSeatId(s, l) }

fun determineFreeSeatId(seatIds: List<Int>): Int {
    val min = seatIds.min()!!
    val max = seatIds.max()!!
    return (min..max).first { s -> !seatIds.contains(s) && seatIds.contains(s + 1) && seatIds.contains(s - 1) }
}
