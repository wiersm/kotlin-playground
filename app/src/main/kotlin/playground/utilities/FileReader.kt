package playground.utilities

import java.io.File
import java.net.URL

class FileReader {
    fun readFile(resource: URL): String {
        return File(resource.file).readText()
    }

    fun readCommaSeparatedValues(resource: URL): Sequence<String> {
        val fileContents = readFile(resource)
        return fileContents.trim().splitToSequence(',')
    }
}

fun resource(fileName: String): URL {
    return FileReader::class.java.getResource(fileName)
}
