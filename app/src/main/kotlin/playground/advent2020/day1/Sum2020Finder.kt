package playground.advent2020.day1

import playground.utilities.Logger

class Sum2020Finder(val logger: Logger) {
    constructor() : this(Logger())

    fun findProduct(entries: Sequence<Int>): Int {
        val list = entries.toList()
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                if (list[i] + list[j] == 2020) {
                    val product = list[i] * list[j]
                    logger.debug("Found items $i (${list[i]}) and $j (${list[j]}), product is $product")
                    return product
                }
            }
        }
        throw IllegalStateException("No combination found that adds up to 2020")
    }

    fun findTriProduct(entries: Sequence<Int>): Int {
        val list = entries.toList()
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                for (k in j + 1 until list.size) {
                    if (list[i] + list[j] + list[k] == 2020) {
                        val product = list[i] * list[j] * list[k]
                        logger.debug("Found items $i (${list[i]}), $j (${list[j]}) and $k (${list[k]}), product is $product")
                        return product
                    }
                }
            }
        }
        throw IllegalStateException("No combination found that adds up to 2020")
    }
}
