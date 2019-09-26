package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest {
    private WebDriver driver;

    @BeforeClass
    public void initTest() throws Exception {
        String browserName = "chrome";
        if ("chrome".equalsIgnoreCase(browserName)){
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", path+"\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if ("ie".equalsIgnoreCase(browserName)) {
            driver = new InternetExplorerDriver();
        } else {
            throw new Exception("Invalid browserName: " + browserName);
        }
    }

    @Test(groups = {"success"})
    public void successGoogleTest() {
        driver.get("https:\\www.google.com");

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("google");
            }
        });
    }

    @Test(groups = {"fail"})
    public void failYahooTest() {
        driver.get("https:\\www.google.com");

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("yahoo");
            }
        });
    }


    @AfterClass
    public void afterTest() {
        driver.close();
    }
}
