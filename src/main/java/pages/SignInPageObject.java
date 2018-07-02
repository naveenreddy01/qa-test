package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class SignInPageObject {

    private WebDriver driver;
    private By email_create = By.id("email_create");
    private By SubmitCreate = By.id("SubmitCreate");
    private By login = By.className("login");
    private By email = By.id("email");
    private By passwd = By.id("passwd");
    private By submitLogin = By.id("SubmitLogin");


    public SignInPageObject(WebDriver webDriver) {
        this.driver = webDriver;
        WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(login)).click();
    }

    public SignInPageObject enterCreateEmailId(String email) {
        driver.findElement(email_create).sendKeys(email);
        return this;
    }

    public SignInPageObject enterLoginEmailId(String value) {
        driver.findElement(email).sendKeys(value);
        return this;
    }

    public SignInPageObject enterLoginPassWord(String value) {
        driver.findElement(passwd).sendKeys(value);
        return this;
    }

    public MyAccountPageObject enterLogin() {
        driver.findElement(submitLogin).click();
        return new MyAccountPageObject(driver);
    }

    public PersonalInfoPageObject clickSubmitCreate() {
        driver.findElement(SubmitCreate).click();
        return new PersonalInfoPageObject(driver);
    }
}
