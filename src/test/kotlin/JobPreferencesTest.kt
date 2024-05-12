import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions

class JobPreferencesTest : BaseTest() {

    private lateinit var pages: Map<WebDriver, Pages>

    private lateinit var jobPreferencesPageUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun getUrl(): String {
        return jobPreferencesPageUrl
    }

    override fun initializePages() {
        pages = drivers.associateWith { Pages(LoginPage(it, waits), JobPreferencesPage(it, waits)) }
    }

    override fun initializeData() {
        jobPreferencesPageUrl = dataProps.getProperty("job.preferences.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email = dataProps.getProperty("email")
        password = dataProps.getProperty("password")
    }

    @Test
    fun selectDisciplines() {
        pages.forEach { (driver, pages) ->
            val wait = waits[driver]
            pages.loginPage.login(email, password)
            wait?.until(ExpectedConditions.urlContains(jobPreferencesPageUrl))

        }
    }

    class Pages(val loginPage: LoginPage, val jobPreferencesPage: JobPreferencesPage)

}