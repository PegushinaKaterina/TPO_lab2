package utils

class Context {
    private var chromeDriver: String? = null
    private var firefoxDriver: String? = null

    fun getChromeDriver(): String? {
        return chromeDriver
    }

    fun setChromeDriver(chromeDriver: String) {
        this.chromeDriver = chromeDriver
    }

    fun getFirefoxDriver(): String? {
        return firefoxDriver
    }

    fun setFirefoxDriver(firefoxDriver: String?) {
        this.firefoxDriver = firefoxDriver
    }
}