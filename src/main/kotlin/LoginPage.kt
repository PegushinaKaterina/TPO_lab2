import exceptions.InvalidLoginOrPasswordException
import org.openqa.selenium.By
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
        waits[driver]?.until(ExpectedConditions.attributeToBe(emailInput, "value", email))
    }

    fun inputPassword(password: String) {
        passwordInput.sendKeys(password)
        waits[driver]?.until(ExpectedConditions.attributeToBe(passwordInput, "value", password))
    }

    fun clickLoginButton() {
        loginButton.click()
    }

    fun login(email: String, password: String) {
        val loginUrl = driver.currentUrl
        inputEmail(email)
        inputPassword(password)
        clickLoginButton()

        try {
            waits[driver]?.until(ExpectedConditions.not(ExpectedConditions.urlContains(loginUrl)))
        } catch (e: Exception) {
            val invalidTextElement =
                driver.findElements(By.xpath("//span[text()= \"Please check your login details and try again.\"]"))
            if (invalidTextElement.isNotEmpty()) {
                throw InvalidLoginOrPasswordException()
            }
        }
    }
}
