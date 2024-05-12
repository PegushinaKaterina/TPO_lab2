import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class LoginPage(private val driver: WebDriver, waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//*[@id=\"username\"]")
    lateinit var emailInput: WebElement

    @FindBy(xpath = "//*[@id=\"password\"]")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[@id=\"javascript-content\"]/div[2]/form/button/div")
    lateinit var loginButton: WebElement

    fun inputEmail(email: String) {
        emailInput.sendKeys(email)
    }

    fun inputPassword(password: String) {
        passwordInput.sendKeys(password)
    }

    fun clickLoginButton() {
        loginButton.click()
    }

    private fun clearAndSendKeys(field: WebElement, text: String) {
        field.clear()
        field.sendKeys(text)
    }

    fun login(email: String, password: String) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        inputEmail(email)
        wait.until(ExpectedConditions.attributeContains(emailInput, "value", email))
        inputPassword(password)
        wait.until(ExpectedConditions.attributeContains(passwordInput, "value", password))
        clickLoginButton()
    }
}
