package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MyAccountPageObject {

    private WebDriver driver;
    private String parentWindowHandler;
    private WebDriverWait wait;
    private By account = By.className("account");
    private By info_account = By.className("info-account");
    private By logout = By.className("logout");

    private By womenButton = By.linkText("Women");
    private By center_column = By.cssSelector("ul.product_list a img");
    private By iframe = By.className("fancybox-iframe");
    private By add_to_cart = By.id("add_to_cart");
    //    private By checkout1 = By.xpath("//span [@title='Continue shopping']/following-sibling:: a/span");
    private By checkout1 = By.partialLinkText("Proceed to checkout");

    private By checkout2 = By.linkText("Proceed to checkout");
    private By processAddress = By.name("processAddress");
    private By uniform = By.id("uniform-cgv");
    private By processCarrier = By.name("processCarrier");
    private By bankwire = By.className("bankwire");
    private By cart_navigation = By.xpath("//*[@id='cart_navigation']/button");

    private By heading = By.cssSelector("h1");
    private By step_done = By.xpath("//li[@class='step_done step_done_last four']");
    private By indent = By.xpath("//*[@class='cheque-indent']/strong");
    private By step_end = By.xpath("//li[@id='step_end' and @class='step_current last']");

    public MyAccountPageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 100);
    }

    public String myAccountGetText() throws InterruptedException {
  //      TimeUnit.MILLISECONDS.sleep(2000);
        /**
         * Need to still improve parallelism
         */
        wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
        return driver.findElement(heading).getText();
    }

    public boolean isStepDoneDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(step_done));
        return driver.findElement(step_done).isDisplayed();
    }

    public boolean isIndentContains(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(indent));
        return driver.findElement(indent).getText().contains(value);
    }

    public boolean stepEndGetText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(step_end));
        return driver.findElement(step_end).isDisplayed();
    }

    public String myAccountGetName() {
        return driver.findElement(account).getText();
    }

    public String accountMessage() {
        return driver.findElement(info_account).getText();
    }

    public boolean logoutBtnIsDisplayed() {
        return driver.findElement(logout).isDisplayed();
    }

    public MyAccountPageObject womenButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(womenButton))).click();
        return this;
    }

    public MyAccountPageObject center_columnClick() {
        driver.findElements(center_column).iterator().next().click();
        return this;
    }

    public MyAccountPageObject swithToFrame() {
        this.parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        driver.switchTo().frame(driver.findElement(iframe));
        return this;
    }

    public MyAccountPageObject addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(add_to_cart))).click();
        return this;
    }

    public MyAccountPageObject checkOut1() throws InterruptedException {
      //  driver.switchTo().defaultContent();
     //   Thread.sleep(2000);
        TimeUnit.MILLISECONDS.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(checkout1))).click();
        return this;
    }

    public MyAccountPageObject checkOut2() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(checkout2))).click();
        return this;
    }

    public MyAccountPageObject processAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(processAddress))).click();
        return this;
    }

    public MyAccountPageObject uniform() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(uniform))).click();
        return this;
    }

    public MyAccountPageObject processCarrier() {
        driver.findElement(processCarrier).click();
        return this;
    }

    public MyAccountPageObject bankWire() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(bankwire))).click();
        return this;
    }

    public MyAccountPageObject cart_navigation() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(cart_navigation))).click();
        return this;
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
