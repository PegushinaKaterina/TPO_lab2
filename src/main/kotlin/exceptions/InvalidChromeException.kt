package exceptions

class InvalidChromeException : RuntimeException() {
    override val message = "Configuration for running Chrome not found."
}