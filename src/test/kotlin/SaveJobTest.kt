import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.test.assertEquals

class SaveJobTest : BaseTest() {

    private lateinit var pagesByDriver: Map<WebDriver, Pages>

    private lateinit var jobsFindUrl: String
    private lateinit var jobsSearchUrl: String
    private lateinit var myJobsUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun getUrl(): String {
        return jobsFindUrl
    }

    override fun initializePages() {
        pagesByDriver =
            drivers.associateWith {
                Pages(
                    LoginPage(it, waits),
                    JobsFindPage(it, waits),
                    MyJobsPage(it, waits),
                )
            }
    }

    override fun initializeData() {
        jobsFindUrl = dataProps.getProperty("jobs.find.url")
        jobsSearchUrl = dataProps.getProperty("jobs.search.url")
        myJobsUrl = dataProps.getProperty("my.jobs.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email = dataProps.getProperty("email")
        password = dataProps.getProperty("password")
    }

    @Test
    fun testSaveJob() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = waits[driver]
            pages.loginPage.login(email, password)
            wait?.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@data-qa=\"search-radius-dropdown\"]")
            ))

            val params = Params("Software Developer", null, Radius.NO_RADIUS)

            findJobs(driver, wait, params.jobTitle, params.location, params.radius)

            val jobName = pages.jobsFindPage.saveFirstJob()

            driver.get(myJobsUrl)
            wait?.until(ExpectedConditions.visibilityOf(pages.myJobsPage.firstJobName))

            val addedJobName = pages.myJobsPage.firstJobName.text
            assertEquals(jobName, addedJobName)

            pages.myJobsPage.removeSavedJob()
        }
    }

    private fun findJobs(
        driver: WebDriver,
        wait: WebDriverWait?,
        jobTitle: String,
        location: String?,
        radius: Radius?
    ) {
        val pages = pagesByDriver[driver]!!
        pages.jobsFindPage.jobsFind(jobTitle, location, radius)

        wait?.until(ExpectedConditions.urlContains(jobsSearchUrl))
    }

    class Pages(val loginPage: LoginPage, val jobsFindPage: JobsFindPage, val myJobsPage: MyJobsPage)
    class Params(val jobTitle: String, val location: String?, val radius: Radius?)
}