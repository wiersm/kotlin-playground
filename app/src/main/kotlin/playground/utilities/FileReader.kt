package playground.utilities

import java.io.File
import java.net.URL
import kotlin.streams.asSequence

class FileReader {
    /** Returns the complete contents of the file. */
    fun readFile(resource: URL): String {
        return file(resource).readText()
    }

    /** Returns the values as a sequence. */
    fun readCommaSeparatedValues(resource: URL): Sequence<String> {
        return splitCommaSeparated(readFile(resource))
    }

    /** Returns a sequence with the lines of the file. */
    fun readLines(resource: URL): Sequence<String> {
        return file(resource).bufferedReader().lines().asSequence()
    }

    /** Returns a sequence with a sequence of values for each line of the file. */
    fun readLinesWithCommaSeparatedValues(resource: URL): Sequence<Sequence<String>> {
        return readLines(resource).map(::splitCommaSeparated)
    }
}

fun resource(fileName: String): URL = FileReader::class.java.getResource(fileName)

fun file(resource: URL): File = File(resource.file)

fun splitCommaSeparated(fileContents: String) = fileContents.trim().splitToSequence(',')
