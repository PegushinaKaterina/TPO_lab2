import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import kotlin.test.assertEquals

class SendContactRequestTest : BaseTest() {

    private lateinit var pagesByDriver: Map<WebDriver, Pages>

    private lateinit var recommendationsPageUrl: String
    private lateinit var sentRequestsUrl: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun getUrl(): String {
        return recommendationsPageUrl
    }

    override fun initializePages() {
        pagesByDriver = drivers.associateWith {
            Pages(
                LoginPage(it, waits),
                RecommendationsPage(it, waits),
                SentRequestsPage(it, waits)
            )
        }
    }

    override fun initializeData() {
        recommendationsPageUrl = dataProps.getProperty("network.recommendations.url")
        sentRequestsUrl = dataProps.getProperty("sent.requests.url")
        firstName = dataProps.getProperty("first.name")
        lastName = dataProps.getProperty("last.name")
        email = dataProps.getProperty("email")
        password = dataProps.getProperty("password")
    }

    @Test
    fun jobsFind() {
        pagesByDriver.forEach { (driver, pages) ->
            val wait = waits[driver]
            pages.loginPage.login(email, password)

            wait?.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-qa=\"network-filtered-recommendations-container\"]/div/div[1]"))
            )

            val addedName = pages.recommendationsPage.addFirstContact()

            driver.get(sentRequestsUrl)

            wait?.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@data-qa=\"requests-sent-list\"]"))
            )

            val requestName = pages.sentRequestsPage.lastRequestName.text
            assertEquals(addedName, requestName)
        }
    }

    class Pages(
        val loginPage: LoginPage,
        val recommendationsPage: RecommendationsPage,
        val sentRequestsPage: SentRequestsPage
    )
}