import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class RecommendationsPage(private val driver: WebDriver, waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//div[@data-qa=\"network-filtered-recommendations-container\"]/div/div[1]//*[text()=\"Add\"]/ancestor::button")
    lateinit var firstAddButton: WebElement

    fun addFirstContact(): String {
        firstAddButton.click()

        val nameTag = driver.findElement(By.xpath("//div[@data-qa=\"network-filtered-recommendations-container\"]/div/div[1]/div/h2"))
        return nameTag.text
    }
}