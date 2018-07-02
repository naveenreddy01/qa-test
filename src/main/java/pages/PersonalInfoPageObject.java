package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInfoPageObject {

    private WebDriver driver;
    private By custFirstName = By.id("customer_firstname");
    private By custLastName = By.id("customer_lastname");
    private By passWord = By.id("passwd");
    private By days = By.id("days");
    private By months = By.id("months");
    private By years = By.id("years");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By city = By.id("city");
    private By id_state = By.id("id_state");
    private By postcode = By.id("postcode");
    private By other = By.id("other");
    private By phone = By.id("phone");
    private By phone_mobile = By.id("phone_mobile");
    private By alias = By.id("alias");
    private By submitAccount = By.id("submitAccount");


    public PersonalInfoPageObject(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10, 50);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender2"))).click();
    }

    public PersonalInfoPageObject customerFirstName(String name) {
        driver.findElement(custFirstName).sendKeys(name);
        return this;
    }

    public PersonalInfoPageObject customerLastName(String surname) {
        driver.findElement(custLastName).sendKeys(surname);
        return this;
    }

    public PersonalInfoPageObject password(String password) {
        driver.findElement(passWord).sendKeys(password);
        return this;
    }

    public PersonalInfoPageObject selectDay(String day) {
        new Select(driver.findElement(days)).selectByValue(day);
        return this;
    }

    public PersonalInfoPageObject selectMonth(String month) {
        new Select(driver.findElement(months)).selectByValue(month);
        return this;
    }

    public PersonalInfoPageObject selectYear(String year) {
        new Select(driver.findElement(years)).selectByValue(year);
        return this;
    }

    public PersonalInfoPageObject enterCompany(String value) {
        driver.findElement(company).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterAddress1(String value) {
        driver.findElement(address1).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterAddress2(String value) {
        driver.findElement(address2).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterCity(String value) {
        driver.findElement(city).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject selectSate(String value) {
        new Select(driver.findElement(id_state)).selectByVisibleText(value);
        return this;
    }

    public PersonalInfoPageObject enterPostCode(String value) {
        driver.findElement(postcode).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterOther(String value) {
        driver.findElement(other).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterPhone(String value) {
        driver.findElement(phone).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterMobile(String value) {
        driver.findElement(phone_mobile).sendKeys(value);
        return this;
    }

    public PersonalInfoPageObject enterAlias(String value) {
        driver.findElement(alias).sendKeys(value);
        return this;
    }

    public MyAccountPageObject clickSubmit() {
        driver.findElement(submitAccount).click();
        return new MyAccountPageObject(driver);
    }
}
