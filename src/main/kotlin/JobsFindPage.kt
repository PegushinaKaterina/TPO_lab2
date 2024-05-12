import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class JobsFindPage(private val driver: WebDriver, private val waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//*[@id=\"keywords-input\"]")
    lateinit var jobTitleInput: WebElement

    @FindBy(xpath = "//*[@id=\"location-input\"]")
    lateinit var locationInput: WebElement

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[1]/div/div/button")
    lateinit var clearLocationButton: WebElement

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/button/div")
    lateinit var radiusSelections: WebElement

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/button/div/span[2]")
    lateinit var radiusSelectionsText: WebElement

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/button[2]/div")
    lateinit var findJobButton: WebElement

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/main/ul/li[1]/article/a")
    lateinit var firstJob: WebElement

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div/div[1]/div/div[4]/div/div/a")
    lateinit var visitButton: WebElement

    fun inputJobTitle(jobTitle: String) {
        jobTitleInput.clear()
        jobTitleInput.sendKeys(jobTitle)
        waits[driver]!!.until(ExpectedConditions.attributeContains(jobTitleInput, "value", jobTitle))
    }

    fun inputLocation(location: String?) {
        clearLocationButton.click()
        location?.let {
            locationInput.sendKeys(location)
            waits[driver]!!.until(ExpectedConditions.attributeContains(locationInput, "value", location))
        }
    }

    fun selectRadius(radius: Radius?) {
        radius?.let {
            if (radiusSelections.isEnabled && radiusSelections.isDisplayed) {
                val wait = WebDriverWait(driver, Duration.ofSeconds(10))
                radiusSelections.click()

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(radius.radiusOptionButtonXPath)))

                val radiusButton =
                    driver.findElement(By.xpath(radius.radiusOptionButtonXPath))
                radiusButton.click()

                wait.until(ExpectedConditions.textToBePresentInElement(radiusSelectionsText, radius.text))
            }
        }

    }

    fun clickFindJobButton() {
        findJobButton.click()
    }

    fun clickFirstJobButton() {
        firstJob.click()
    }

    fun clickVisitButton() {
        visitButton.click()
    }

    fun jobsFind(jobTitle: String, location: String?, radius: Radius?, jobsSearchUrl: String) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        inputJobTitle(jobTitle)
        selectRadius(radius)
        inputLocation(location)

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
}
