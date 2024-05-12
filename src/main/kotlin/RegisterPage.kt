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

    @FindBy(xpath = "//*[@id=\"javascript-content\"]/div[2]/div/div/div[1]/div/div/form/button/div")
    lateinit var continueButton: WebElement

    fun inputFirstName(firstName: String) {
        firstNameInput.sendKeys(firstName)
    }

    fun inputLastName(lastName: String) {
        lastNameInput.sendKeys(lastName)
    }

    fun inputEmail(email: String) {
        emailInput.sendKeys(email)
    }

    fun inputPassword(password: String) {
        passwordInput.sendKeys(password)
    }

    fun clickContinueButton() {
        continueButton.click()
    }

    fun register(firstName: String, lastName: String, email: String, password: String) {
        inputFirstName(firstName)
        inputLastName(lastName)
        inputEmail(email)
        inputPassword(password)
        clickContinueButton()
    }
}