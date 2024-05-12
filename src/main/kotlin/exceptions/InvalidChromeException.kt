package exceptions

import java.lang.RuntimeException

class InvalidChromeException: RuntimeException() {
    override val message = "Configuration for running Chrome not found."
}