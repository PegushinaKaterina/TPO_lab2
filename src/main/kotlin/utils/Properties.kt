package utils

import exceptions.InvalidChromeException
import java.io.FileInputStream
import java.util.Properties

class Properties private constructor() {

    fun reading(context: Context) {
        val props = Properties()

        props.load(FileInputStream("src/browsers.properties"))
        if (props.getProperty(WEBDRIVER_CHROME_DRIVER) != null) {
            if (props.getProperty(WEBDRIVER_CHROME_DRIVER) == CHROME_DRIVER) {
                context.setChromeDriver(props.getProperty(WEBDRIVER_CHROME_DRIVER))
            }
        } else {
            throw InvalidChromeException()
        }
        if (props.getProperty(WEBDRIVER_FIREFOX_DRIVER) != null) {
            if (props.getProperty(WEBDRIVER_FIREFOX_DRIVER) == FIREFOX_FIREFOX) {
                context.setFirefoxDriver(props.getProperty(WEBDRIVER_FIREFOX_DRIVER))
            }
        } else {
            // throw InvalidFirefoxException()
        }
    }

    companion object {
        const val WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver"
        const val WEBDRIVER_FIREFOX_DRIVER = "webdriver.firefox.driver"
        const val CHROME_DRIVER = "chromeDriver"
        const val FIREFOX_FIREFOX = "firefoxDriver"

        private var instance: utils.Properties? = null

        fun getInstance(): utils.Properties {
            if (instance == null) {
                instance = utils.Properties()
            }
            return instance!!
        }
    }
}