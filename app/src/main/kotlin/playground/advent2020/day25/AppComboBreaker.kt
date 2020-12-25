package playground.advent2020.day25

import playground.utilities.LogLevel
import playground.utilities.Logger
import playground.utilities.UI

fun main() {
    val encryptionKey = ComboBreaker(Logger(LogLevel.INFO)).findEncryptionKey(1614360, 7734663)
    UI().printProgramResult("Encryption key is: $encryptionKey")
}
