package playground.advent2020.day2

import playground.utilities.Logger

class ValidPasswordCounter(val logger: Logger) {
    constructor() : this(Logger())

    fun countValidPasswordsForSledCompany(input: Sequence<String>) : Int {
        var nrOfValidPasswords = 0
        input.forEach {
            val split1 = it.split(':')
            val password = split1[1].trim()
            logger.debug("Password is $password")

            val split2 = split1[0].split(' ')
            val requiredCharacter = split2[1][0]
            logger.debug("Required character is $requiredCharacter")

            val split3 = split2[0].split('-')
            val minOccurs = split3[0].toInt()
            val maxOccurs = split3[1].toInt()
            logger.debug("Allowed occurrences: min $minOccurs, max $maxOccurs")

            val nrOfOccurrences = password.filter { c -> c == requiredCharacter }.count()
            logger.debug("Number of occurrences: $nrOfOccurrences")

            if (nrOfOccurrences in minOccurs..maxOccurs) {
                logger.debug("Password is valid")
                nrOfValidPasswords++
            } else {
                logger.debug("Password is invalid")
            }
        }
        return nrOfValidPasswords
    }

    fun countValidPasswordsForTobogganCompany(input: Sequence<String>): Int {
        var nrOfValidPasswords = 0
        input.forEach {
            val split1 = it.split(':')
            val password = split1[1].trim()
            logger.debug("Password is $password")

            val split2 = split1[0].split(' ')
            val requiredCharacter = split2[1][0]
            logger.debug("Required character is $requiredCharacter")

            val split3 = split2[0].split('-')
            val index1 = split3[0].toInt()
            val index2 = split3[1].toInt()
            logger.debug("Indexes to check: $index1 and $index2")

            val index1Matches = password[index1 - 1] == requiredCharacter
            val index2Matches = password[index2 - 1] == requiredCharacter

            if ((index1Matches && !index2Matches) || (!index1Matches && index2Matches)) {
                logger.debug("Password is valid")
                nrOfValidPasswords++
            } else {
                logger.debug("Password is invalid")
            }
        }
        return nrOfValidPasswords
    }

}
