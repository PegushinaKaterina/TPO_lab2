package exceptions

import java.lang.RuntimeException

class InvalidPropertiesException: RuntimeException() {
    override val message = "Empty properties file."
}