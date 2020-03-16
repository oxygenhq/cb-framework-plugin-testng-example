package Selenium;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.CbTestNg {
    @BeforeClass
    public void initTest() throws Exception {
        WebDriver driver = createWebDriverBasedOnCbCapabilities();
        setWebDriver(driver);
    }

    @Test(groups = {"success"})
    public void successGoogleTest() {
        startStep("123");
        startStep("345");
        driver.get("https://www.google.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
        endStep("123");
    }

    @Test(groups = {"fail"})
    public void failYahooTest() {
        startStep("345");
        driver.get("https://www.google.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"success"})
    public void successNoYahooTest() {
        driver.get("https://www.google.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"success"})
    public void successYahooTest() {
        driver.get("https://www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("yahoo"));
    }

    @Test(groups = {"fail"})
    public void failGoogleTest() {
        driver.get("https://www.yahoo.com");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test(groups = {"success"})
    public void successNoGoogleTest() {
        driver.get("https://www.yahoo.com");
        Assert.assertFalse(driver.getTitle().toLowerCase().contains("google"));
    }

    @AfterClass
    public void afterTest() {
        if(driver != null)
            driver.close();
    }
}
