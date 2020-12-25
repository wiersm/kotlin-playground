package playground.advent2020.day25

import playground.utilities.Logger

const val PUBLIC_KEY_SUBJECT_NUMBER = 7
const val MODULO_OPERAND = 20201227

class ComboBreaker(val l: Logger) {
    fun findEncryptionKey(cardPublicKey: Int, doorPublicKey: Int): Long {
        // Find the card's loop size by brute forcing its public key.
        var cardLoopSize = 0
        var valueForSearch = 1L
        do {
            cardLoopSize++
            valueForSearch = transformValue(valueForSearch, PUBLIC_KEY_SUBJECT_NUMBER)
            l.debug("Loop $cardLoopSize: $valueForSearch")
        } while (valueForSearch.toInt() != cardPublicKey)

        l.info("Card loop size is $cardLoopSize")

        // Now calculate the encryption key by applying the algorithm
        var encryptionKey = 1L
        repeat(cardLoopSize) {
            encryptionKey = transformValue(encryptionKey, doorPublicKey)
        }

        l.info("Encryption key is $encryptionKey")

        return encryptionKey
    }

    private fun transformValue(value: Long, subjectNumber: Int): Long = (value * subjectNumber) % MODULO_OPERAND
}
