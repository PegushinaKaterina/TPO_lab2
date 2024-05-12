import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class JobsFindPage(private val driver: WebDriver, private val waits: Map<WebDriver, WebDriverWait>) :
    Page(driver, waits) {

    @FindBy(xpath = "//*[@id=\"keywords-input\"]")
    lateinit var jobTitleInput: WebElement

    @FindBy(xpath = "//*[@id=\"location-input\"]")
    lateinit var locationInput: WebElement

    @FindBy(xpath = "//*[@data-xds=\"IconCross\"]/ancestor::button[@type=\"reset\"]")
    lateinit var clearLocationButton: WebElement

    @FindBy(xpath = "//button[@data-qa=\"search-radius-dropdown\"]")
    lateinit var radiusSelections: WebElement

    @FindBy(xpath = "//button[@data-qa=\"search-radius-dropdown\"]/div/span[last()]")
    lateinit var radiusSelectionsText: WebElement

    @FindBy(xpath = "//button[@data-qa=\"search-button\"][@type=\"submit\"]")
    lateinit var findJobButton: WebElement

    @FindBy(xpath = "//main/ul/li[1]/article/a")
    lateinit var firstJob: WebElement

    @FindBy(xpath = "//*[text()=\"Visit employer website\"]/ancestor::a")
    lateinit var visitButton: WebElement

    fun jobSelect(): Boolean {
        val wait = waits[driver]
        val resultList = driver.findElements(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/main/ul"))

        return if (resultList.isNotEmpty()) {
            wait?.until(ExpectedConditions.elementToBeClickable(firstJob))
            clickFirstJobButton()

            wait?.until(ExpectedConditions.elementToBeClickable(visitButton))
            clickVisitButton()
            true
        } else {
            false
        }
    }

    fun jobsFind(jobTitle: String, location: String?, radius: Radius?) {
        selectRadius(radius)
        inputLocation(location)
        inputJobTitle(jobTitle)
        clickFindJobButton()
    }

    private fun inputJobTitle(jobTitle: String) {
        jobTitleInput.clear()
        waits[driver]?.until(ExpectedConditions.attributeToBe(jobTitleInput, "value", ""))

        jobTitleInput.sendKeys(jobTitle)
        waits[driver]?.until(ExpectedConditions.attributeToBe(jobTitleInput, "value", jobTitle))
    }

    private fun inputLocation(location: String?) {
        clearLocationButton.click()
        waits[driver]?.until(ExpectedConditions.attributeToBe(locationInput, "value", ""))

        location?.let {
            locationInput.sendKeys(location)
            waits[driver]?.until(ExpectedConditions.attributeContains(locationInput, "value", location))
        }
    }

    private fun selectRadius(radius: Radius?) {
        radius?.let {
            if (radiusSelections.isEnabled && radiusSelections.isDisplayed) {
                val wait = waits[driver]
                radiusSelections.click()

                wait?.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(radius.buttonXPath)))

                val radiusButton =
                    driver.findElement(By.xpath(radius.buttonXPath))
                radiusButton.click()

                wait?.until(ExpectedConditions.textToBePresentInElement(radiusSelectionsText, radius.text))
            }
        }
    }

    private fun clickFindJobButton() {
        findJobButton.click()
    }

    private fun clickFirstJobButton() {
        firstJob.click()
    }

    private fun clickVisitButton() {
        visitButton.click()
    }
}
