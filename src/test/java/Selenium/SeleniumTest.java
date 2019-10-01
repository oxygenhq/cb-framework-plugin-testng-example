package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.TestNGRunner {
    private WebDriver driver;

    @BeforeClass
    public void initTest() throws Exception {
        String browserName = "chrome";
        if ("chrome".equalsIgnoreCase(browserName)) {
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", path + "\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if("firefox".equalsIgnoreCase(browserName)){
            driver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browserName)) {
            driver = new InternetExplorerDriver();
        } else {
            throw new Exception("Invalid browserName: " + browserName);
        }

        setWebDriver(driver);
    }

    @Test(groups = {"success"})
    public void successGoogleTest() {
        driver.get("https:\\www.google.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test(groups = {"fail"})
    public void failYahooTest() {
        driver.get("https:\\www.google.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"success"})
    public void successNoYahooTest() {
        driver.get("https:\\www.google.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"success"})
    public void successYahooTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"fail"})
    public void failGoogleTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test(groups = {"success"})
    public void successNoGoogleTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("google"));
    }

    @AfterClass
    public void afterTest() {
        driver.close();
    }
}
