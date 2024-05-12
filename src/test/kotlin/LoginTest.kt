import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.test.assertEquals

class LoginTest : BaseTest() {

    private lateinit var loginPageByDriver: Map<WebDriver, LoginPage>

    private lateinit var loginPageUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var jobsFindUrl: String

    override fun getUrl(): String {
        return loginPageUrl
    }

    override fun initializePages() {
        loginPageByDriver = drivers.associateWith { LoginPage(it, waits) }
    }

    override fun initializeData() {
        loginPageUrl = dataProps.getProperty("login.page.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email= dataProps.getProperty("email")
        password = dataProps.getProperty("password")
        jobsFindUrl = dataProps.getProperty("jobs.find.url")
    }

    @Test
    fun login() {
        loginPageByDriver.forEach { (driver, loginPage) ->
            loginPage.login(email, password)

            val wait = WebDriverWait(driver, Duration.ofSeconds(20))
            wait.until(ExpectedConditions.urlContains(jobsFindUrl))

            val currentUrl = driver.currentUrl
            assertEquals(jobsFindUrl, currentUrl)
        }
    }
}