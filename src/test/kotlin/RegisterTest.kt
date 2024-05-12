import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import kotlin.test.assertEquals

class RegisterTest : BaseTest() {

    private lateinit var loginPageByDriver: Map<WebDriver, LoginPage>

    private lateinit var registerPageByDriver: Map<WebDriver, RegisterPage>

    private lateinit var registerPageUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var notRobotUrl: String

    override fun getUrl(): String {
        return registerPageUrl
    }

    override fun initializePages() {
        registerPageByDriver = drivers.associateWith { RegisterPage(it, waits) }
    }

    override fun initializeData() {
        registerPageUrl = dataProps.getProperty("register.page.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email= dataProps.getProperty("email")
        password = dataProps.getProperty("password")
        notRobotUrl = dataProps.getProperty("not.robot.url")
    }

    @Test
    fun register() {
        registerPageByDriver.forEach { (driver, registerPage) ->
            registerPage.register(firstName, lastName, email, password)

            val currentUrl = driver.currentUrl
            assertEquals(notRobotUrl, currentUrl)
        }
    }
}