import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class MyJobsPage(private val driver: WebDriver, private val waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//h3[@data-cy=\"job-teaser-list-title\"][1]")
    lateinit var firstJobName: WebElement

    @FindBy(xpath = "//button[@data-qa=\"more-actions-trigger\"][1]")
    lateinit var firstMoreActionsButton: WebElement

    @FindBy(xpath = "//*[text()=\"Unsave job\"][1]/ancestor::button")
    lateinit var firstUnsaveButton: WebElement

    fun removeSavedJob() {
        val wait = waits[driver]
        firstMoreActionsButton.click()

        wait?.until(ExpectedConditions.visibilityOf(firstUnsaveButton))

        firstUnsaveButton.click()

        wait?.until(ExpectedConditions.invisibilityOf(firstJobName))
    }

}
