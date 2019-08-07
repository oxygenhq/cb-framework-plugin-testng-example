import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        String browserName = System.getProperty("browserName");
        if ("chrome".equalsIgnoreCase(browserName)){
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
        } else if ("ie".equalsIgnoreCase(browserName)) {
            driver = new InternetExplorerDriver();
        } else {
            throw new Exception("Invalid browserName: " + browserName);
        }
    }

    @Test
    public void checkTitle1() {
        driver.get("https:\\www.google.com");

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("google");
            }
        });
    }

    @Test
    public void checkTitle2() {
        driver.get("https:\\www.google.com");

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !d.getTitle().toLowerCase().contains("yahoo");
            }
        });
    }

    @AfterClass
    public void afterTest() {
        driver.close();
    }
}
