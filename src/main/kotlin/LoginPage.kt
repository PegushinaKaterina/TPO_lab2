import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val driver: WebDriver, private val waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//*[@id=\"username\"]")
    lateinit var emailInput: WebElement

    @FindBy(xpath = "//*[@id=\"password\"]")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[text()=\"Log in\"]/ancestor::button")
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

    fun login(email: String, password: String) {
        val wait = waits[driver]
        inputEmail(email)
        wait?.until(ExpectedConditions.attributeContains(emailInput, "value", email))
        inputPassword(password)
        wait?.until(ExpectedConditions.attributeContains(passwordInput, "value", password))
        clickLoginButton()
    }
}
