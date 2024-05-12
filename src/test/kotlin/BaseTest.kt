import exceptions.InvalidPropertiesException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.WebDriverWait
import utils.Context
import utils.Properties
import java.io.FileInputStream
import java.lang.Thread.sleep
import java.time.Duration
import java.util.Properties as JavaProperties

abstract class BaseTest {
    lateinit var context: Context
    lateinit var drivers: List<WebDriver>
    lateinit var dataProps: JavaProperties
    lateinit var waits: Map<WebDriver, WebDriverWait>

    @BeforeEach
    open fun setup() {
        sleep(10000)
        context = Context()
        drivers = emptyList()
        dataProps = JavaProperties()
        dataProps.load(FileInputStream("src/test/resources/data.properties"))
        initializeData()

        Properties.getInstance().reading(context)
        context.getChromeDriver()?.let {
            drivers = drivers + ChromeDriver()
        }

        context.getFirefoxDriver()?.let {
            drivers = drivers + FirefoxDriver()
        }

        if (drivers.isEmpty()) throw InvalidPropertiesException()

        val waitMutableMap = mutableMapOf<WebDriver, WebDriverWait>()
        drivers.forEach {
            //it.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
            waitMutableMap[it] = WebDriverWait(it, Duration.ofSeconds(10))
        }
        waits = waitMutableMap

        initializePages()

        val url = getUrl()
        drivers.forEach {
            it.get(url)
            val acceptButton = it.findElement(By.xpath("//*[@id=\"consent-accept-button\"]/div"))
            acceptButton.click()
        }
    }

    abstract fun getUrl(): String
    abstract fun initializePages()
    abstract fun initializeData()

    @AfterEach
    fun tearDown() {
        drivers.forEach { it.quit() }
    }
}