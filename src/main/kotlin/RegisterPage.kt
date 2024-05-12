import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class RegisterPage(private val driver: WebDriver, waits: Map<WebDriver, WebDriverWait>) : Page(driver, waits) {

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    lateinit var firstNameInput: WebElement

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    lateinit var lastNameInput: WebElement

    @FindBy(xpath = "//*[@id=\"email\"]")
    lateinit var emailInput: WebElement

    @FindBy(xpath = "//*[@id=\"password\"]")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[text()=\"Continue\"]/ancestor::button")
    lateinit var continueButton: WebElement

    fun register(firstName: String, lastName: String, email: String, password: String) {
        inputFirstName(firstName)
        inputLastName(lastName)
        inputEmail(email)
        inputPassword(password)
        clickContinueButton()
    }

    private fun inputFirstName(firstName: String) {
        firstNameInput.sendKeys(firstName)
    }

    private fun inputLastName(lastName: String) {
        lastNameInput.sendKeys(lastName)
    }

    private fun inputEmail(email: String) {
        emailInput.sendKeys(email)
    }

    private fun inputPassword(password: String) {
        passwordInput.sendKeys(password)
    }

    private fun clickContinueButton() {
        continueButton.click()
    }
}