package org.naic.mfl.se.challenge;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MyAccountPageObject;
import pages.PersonalInfoPageObject;
import pages.SignInPageObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class WebTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private WebDriverWait wait;
    private SignInPageObject signInPageObject;
    private PersonalInfoPageObject personalInfoPageObject;
    private MyAccountPageObject myAccountPageObject;

    String existingUserEmail = "mflsqe@naic.org";
    String existingUserPassword = "mflsqe1234";

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        /**
         * For Mac Machines
         */

        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        /**
         * for Windows Machines
         */
        //  System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
        driver.set(new ChromeDriver());
        wait = new WebDriverWait(getDriver(), 10, 10);
        getDriver().get("http://automationpractice.com/index.php");
    }

    @Test
    public void signInTest() throws InterruptedException {
        signInPageObject = new SignInPageObject(getDriver());
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String name = "Firstname";
        String surname = "Lastname";
        personalInfoPageObject = signInPageObject.enterCreateEmailId(email).clickSubmitCreate();
        personalInfoPageObject.customerFirstName(name).customerLastName(surname).password("Qwerty");
        personalInfoPageObject.selectDay("1").selectMonth("1").selectYear("2000");
        personalInfoPageObject.enterCompany("Company")
                .enterAddress1("Qwerty, 123")
                .enterAddress2("zxcvb")
                .enterCity("Qwerty")
                .selectSate("Colorado")
                .enterPostCode("12345")
                .enterOther("Qwerty")
                .enterPhone("12345123123")
                .enterMobile("12345123123")
                .enterAlias("hf");
        myAccountPageObject = personalInfoPageObject.clickSubmit();
        assertEquals(myAccountPageObject.myAccountGetText(), "MY ACCOUNT");
        assertEquals(myAccountPageObject.myAccountGetName(), name + " " + surname);
        assertTrue(myAccountPageObject.accountMessage().contains("Welcome to your account."));
        assertTrue(myAccountPageObject.logoutBtnIsDisplayed());
        assertTrue(myAccountPageObject.getCurrentURL().contains("controller=my-account"));
    }

    @Test
    public void logInTest() throws InterruptedException {
        String fullName = "mfl sqe";
        signInPageObject = new SignInPageObject(getDriver());
        myAccountPageObject = signInPageObject.enterLoginEmailId(existingUserEmail)
                .enterLoginPassWord(existingUserPassword)
                .enterLogin();
        assertEquals("MY ACCOUNT", myAccountPageObject.myAccountGetText());
        assertEquals(fullName, myAccountPageObject.myAccountGetName());
        assertTrue(myAccountPageObject.accountMessage().contains("Welcome to your account."));
        assertTrue(myAccountPageObject.logoutBtnIsDisplayed());
        assertTrue(myAccountPageObject.getCurrentURL().contains("controller=my-account"));

    }

    @Test
    public void checkoutTest() throws InterruptedException {
        signInPageObject = new SignInPageObject(getDriver());
        myAccountPageObject = signInPageObject.enterLoginEmailId(existingUserEmail)
                .enterLoginPassWord(existingUserPassword)
                .enterLogin();
        myAccountPageObject.womenButtonClick()
                .center_columnClick()
                .swithToFrame()
                .addToCart()
                .checkOut1()
                .checkOut2()
                .processAddress()
                .uniform()
                .processCarrier()
                .bankWire()
                .cart_navigation();

        assertEquals("ORDER CONFIRMATION", myAccountPageObject.myAccountGetText());
        assertTrue(myAccountPageObject.isStepDoneDisplayed());
        assertTrue(myAccountPageObject.stepEndGetText());
        assertTrue(myAccountPageObject.isIndentContains("Your order on My Store is complete."));
        assertTrue(myAccountPageObject.getCurrentURL().contains("controller=order-confirmation"));
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("src/main/resources/" + getDriver().getTitle() + ".png");
            FileUtils.copyFile(SrcFile, DestFile);
        }

        getDriver().quit();
    }
}
