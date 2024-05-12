package exceptions

class InvalidPropertiesException : RuntimeException() {
    override val message = "Empty properties file."
}