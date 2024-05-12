package exceptions

import java.lang.RuntimeException

class InvalidFirefoxException: RuntimeException() {
    override val message = "Configuration for running Firefox not found."
}
