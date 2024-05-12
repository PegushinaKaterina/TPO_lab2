package exceptions

class InvalidFirefoxException : RuntimeException() {
    override val message = "Configuration for running Firefox not found."
}
