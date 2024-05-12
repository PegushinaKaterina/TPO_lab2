import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class JobsFindTest : BaseTest() {

    private lateinit var pagesByDriver: Map<WebDriver, Pages>

    private lateinit var jobsFindUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var jobsSearchUrl: String

    private val jobTitle = "Software Developer"
    private val location = null
    private val radius = Radius.NO_RADIUS

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
        email= dataProps.getProperty("email")
        password = dataProps.getProperty("password")
        jobsSearchUrl = dataProps.getProperty("jobs.search.url")
    }

    @Test
    fun jobsFind() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = WebDriverWait(driver, Duration.ofSeconds(10))

            pages.loginPage.login(email, password)

            pages.jobsFindPage.jobsFind(jobTitle, location, radius, jobsSearchUrl)

            wait.until(ExpectedConditions.titleContains(jobTitle))
            val currentTitle = driver.title
            assert(currentTitle.contains(jobTitle))
        }
    }

    @Test
    fun jobsFind2() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = WebDriverWait(driver, Duration.ofSeconds(10))
            pages.loginPage.login(email, password)
            wait.until(ExpectedConditions.urlContains(jobsFindUrl))

            val paramsList = listOf(
       //         Params("Software Developer", null, Radius.NO_RADIUS),
                Params("Manager", "St Petersburg", Radius.KM_50)
            )

            paramsList.forEach {
                driver.get(jobsFindUrl)
                jobsFind(driver, wait, it.jobTitle, it.location, it.radius)
            }
        }
    }

    fun jobsFind(driver: WebDriver, wait: WebDriverWait, jobTitle: String, location: String?, radius: Radius?) {
            val pages = pagesByDriver.get(driver)!!
            pages.jobsFindPage.jobsFind(jobTitle, location, radius, jobsSearchUrl)

            wait.until(ExpectedConditions.titleContains(jobTitle))
            val currentTitle = driver.title
            assert(currentTitle.contains(jobTitle))
    }



    fun jobsFind(wait: WebDriverWait, jobTitle: String, location: String?, radius: Radius?, jobsSearchUrl: String) {
        inputJobTitle(jobTitle)
        wait.until(ExpectedConditions.attributeContains(jobTitleInput, "value", jobTitle))

        selectRadius(radius)
        inputLocation(location)
        location?.let { wait.until(ExpectedConditions.attributeContains(locationInput, "value", location)) }

        clickFindJobButton()
        wait.until(ExpectedConditions.urlContains(jobsSearchUrl))
        val resultList = driver.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/main/ul"))

        if(resultList.isNotEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(firstJob))
            clickFirstJobButton()

            wait.until(ExpectedConditions.elementToBeClickable(visitButton))
            clickVisitButton()
        }
    }

    fun jobsFind(jobsFindPage: JobsFindPage, jobTitle: String, location: String?, radius: Radius?, jobsSearchUrl: String) {
        jobsFindPage.inputJobTitle(jobTitle)
        jobsFindPage.
        wait.until(ExpectedConditions.attributeContains(jobTitleInput, "value", jobTitle))

        selectRadius(radius)
        inputLocation(location)
        location?.let { wait.until(ExpectedConditions.attributeContains(locationInput, "value", location)) }

        clickFindJobButton()
        wait.until(ExpectedConditions.urlContains(jobsSearchUrl))
        val resultList = driver.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/main/ul"))

        if(resultList.isNotEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(firstJob))
            clickFirstJobButton()

            wait.until(ExpectedConditions.elementToBeClickable(visitButton))
            clickVisitButton()
        }
    }


    class Pages(val loginPage: LoginPage, val jobsFindPage: JobsFindPage)
    class Params(val jobTitle: String, val location: String?, val radius: Radius?)
}