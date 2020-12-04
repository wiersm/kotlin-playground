package playground.advent2020.day4

import playground.utilities.Logger

class PassportValidator(val logger: Logger) {
    // Required fields *without* cid.
    private val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    constructor() : this(Logger())

    fun countValidPassports(input: Sequence<String>, validateFieldValues: Boolean) : Int {
        var nrOfValidPassports = 0
        var fields = mutableMapOf<String, String>()
        input.forEach {
            line ->
            if (line.isBlank()) {
                nrOfValidPassports += validatePassport(fields, validateFieldValues)
                fields = mutableMapOf<String, String>()
            } else {
                line.split(' ').forEach {
                    keyValue ->
                    val pair = keyValue.split(':')
                    fields[pair[0]] = pair[1]
                }
            }
        }

        nrOfValidPassports += validatePassport(fields, validateFieldValues)

        return nrOfValidPassports
    }

    private fun validatePassport(fields: MutableMap<String, String>, validateFieldValues: Boolean): Int {
        var isValid = true

        logger.debug("Fields for this passport: $fields")
        if (!fields.keys.containsAll(requiredFields)) {
            logger.debug("Passport is invalid: not all required fields present")
            return 0
        }

        if (!validateFieldValues) {
            logger.debug("Passport is valid")
            return 1
        }

        val byr = fields["byr"]!!
        if (!byr.matches(Regex("""\d\d\d\d""")) || byr.toInt() < 1920 || byr.toInt() > 2002) {
            logger.debug("Invalid byr: $byr")
            isValid = false
        }

        val iyr = fields["iyr"]!!
        if (!iyr.matches(Regex("""\d\d\d\d""")) || iyr.toInt() < 2010 || iyr.toInt() > 2020) {
            logger.debug("Invalid iyr: $iyr")
            isValid = false
        }

        val eyr = fields["eyr"]!!
        if (!eyr.matches(Regex("""\d\d\d\d""")) || eyr.toInt() < 2020 || eyr.toInt() > 2030) {
            logger.debug("Invalid eyr: $eyr")
            isValid = false
        }

        val hgt = fields["hgt"]!!
        if (hgt.matches(Regex("""\d\d\dcm"""))) {
            val hgtValue = hgt.substring(0..2).toInt()
            logger.debug("Height in centimeters: $hgtValue")
            if (!(hgtValue in 150..193)) {
                logger.debug("Invalid hgt: $hgt")
                isValid = false
            }
        } else if (hgt.matches(Regex("""\d\din"""))) {
            val hgtValue = hgt.substring(0..1).toInt()
            logger.debug("Height in inches: $hgtValue")
            if (!(hgtValue in 59..76)) {
                logger.debug("Invalid hgt: $hgt")
                isValid = false
            }
        } else {
            logger.debug("Invalid hgt: $hgt")
            isValid = false
        }

        val hcl = fields["hcl"]!!
        if (!hcl.matches(Regex("""#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]"""))) {
            logger.debug("Invalid hcl: $hcl")
            isValid = false
        }

        val ecl = fields["ecl"]!!
        val validEcls = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        if (!validEcls.contains(ecl)) {
            logger.debug("Invalid ecl: $ecl")
            isValid = false
        }

        val pid = fields["pid"]!!
        if (!pid.matches(Regex("""\d\d\d\d\d\d\d\d\d"""))) {
            logger.debug("Invalid pid: $pid")
            isValid = false
        }

        if (!isValid) return 0

        logger.debug("Passport is valid")
        return 1
    }

}
