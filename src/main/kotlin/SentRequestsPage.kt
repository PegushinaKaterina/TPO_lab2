import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class SentRequestsPage(private val driver: WebDriver, waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//*[@data-qa=\"requests-sent-list\"]/div[1]/div/a/div[2]/div[1]/p")
    lateinit var lastRequestName: WebElement

}