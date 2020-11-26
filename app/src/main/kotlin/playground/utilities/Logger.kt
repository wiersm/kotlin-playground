package playground.utilities

enum class LogLevel {
    NONE,
    INFO,
    DEBUG,
}

class Logger(val logLevel: LogLevel) {
    constructor() : this(LogLevel.NONE)

    fun info(message: String) {
        if (logLevel >= LogLevel.INFO) println("INFO: $message")
    }

    fun debug(message: String) {
        if (logLevel >= LogLevel.DEBUG) println("DEBUG: $message")
    }

}
