import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

open class Page(private val driver: WebDriver, private val waits: Map<WebDriver, WebDriverWait>) {

    init {
        PageFactory.initElements(driver, this)
    }
}