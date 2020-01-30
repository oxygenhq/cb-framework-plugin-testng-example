package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.URL;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.TestNGRunner {
    private WebDriver driver;

    @BeforeClass
    public void initTest() throws Exception {
        String browserName = System.getProperty("browserName");
        DesiredCapabilities capabilities = null;
        if("firefox".equalsIgnoreCase(browserName)){
            capabilities = DesiredCapabilities.firefox();
        } else if ("ie".equalsIgnoreCase(browserName)) {
            capabilities = DesiredCapabilities.internetExplorer();
        } else {
            capabilities = DesiredCapabilities.chrome();
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        setWebDriver(driver);
    }

    @Test(groups = {"success", "e2e"})
    public void successGoogleTest() {
        driver.get("https:\\www.google.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test(groups = {"fail", "e2e"})
    public void failYahooTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo1"));
    }

    @Test(groups = {"success", "e2e"})
    public void successNoYahooTest() {
        driver.get("https:\\www.google.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"success", "e2e"})
    public void successYahooTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"fail", "e2e"})
    public void failGoogleTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test(groups = {"success", "e2e"})
    public void successNoGoogleTest() {
        driver.get("https:\\www.yahoo.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("google"));
    }

    @AfterClass
    public void afterTest() {
        driver.close();
    }
}
