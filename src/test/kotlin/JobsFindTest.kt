import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.test.assertTrue

class JobsFindTest : BaseTest() {

    private lateinit var pagesByDriver: Map<WebDriver, Pages>

    private lateinit var jobsFindUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var jobsSearchUrl: String

    override fun getUrl(): String {
        return jobsFindUrl
    }

    override fun initializePages() {
        pagesByDriver = drivers.associateWith { Pages(LoginPage(it, waits), JobsFindPage(it, waits)) }
    }

    override fun initializeData() {
        jobsFindUrl = dataProps.getProperty("jobs.find.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email = dataProps.getProperty("email")
        password = dataProps.getProperty("password")
        jobsSearchUrl = dataProps.getProperty("jobs.search.url")
    }

    @Test
    fun testFindExistingJob() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = waits[driver]
            pages.loginPage.login(email, password)
            wait?.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@data-qa=\"search-radius-dropdown\"]")
            ))

            val params = Params("Software Developer", null, Radius.NO_RADIUS)

            jobsFind(driver, wait, params.jobTitle, params.location, params.radius)
            jobSelect(driver, wait, params.jobTitle)
        }
    }

    @Test
    fun testFindUnexistingJob() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = waits[driver]
            pages.loginPage.login(email, password)
            wait?.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@data-qa=\"search-radius-dropdown\"]")
            ))

            val params = Params("Manager", "St Petersburg", Radius.KM_50)

            jobsFind(driver, wait, params.jobTitle, params.location, params.radius)
            jobSelect(driver, wait, params.jobTitle)
        }
    }

    private fun jobsFind(
        driver: WebDriver,
        wait: WebDriverWait?,
        jobTitle: String,
        location: String?,
        radius: Radius?
    ) {
        val pages = pagesByDriver[driver]!!
        pages.jobsFindPage.jobsFind(jobTitle, location, radius)

        wait?.until(ExpectedConditions.urlContains(jobsSearchUrl))
        val currentUrl = driver.currentUrl
        assertTrue(currentUrl.contains(jobsSearchUrl))
    }

    private fun jobSelect(driver: WebDriver, wait: WebDriverWait?, jobTitle: String) {
        val pages = pagesByDriver[driver]!!
        if (pages.jobsFindPage.jobSelect()) {
            wait?.until(ExpectedConditions.titleContains(jobTitle))
            val currentTitle = driver.title
            assertTrue(currentTitle.contains(jobTitle))
        }
    }

    class Pages(val loginPage: LoginPage, val jobsFindPage: JobsFindPage)
    class Params(val jobTitle: String, val location: String?, val radius: Radius?)
}