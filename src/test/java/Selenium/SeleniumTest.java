package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.CbTestNg {

    private String harelHomeUrl = "https://www.harel-group.co.il/Pages/default.aspx";

    @BeforeClass
    public void initTest() throws Exception {
        WebDriver driver = createWebDriverBasedOnCbCapabilities();
        setWebDriver(driver);
    }

    @Test
    public void TestAppPageReachable() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='חיפוש אודות סטטוס תביעה']")).click();
        driver.findElement(By.xpath("//a[@title='הגשתי תביעה במסגרת פוליסת ביטוח למקרה מוות. איך אוכל לברר מה מצב התביעה?']")).click();
        driver.findElement(By.xpath("//a[@title='קידוד קופות']")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("app"));
    }

    @Test
    public void PensionPageReachable() {
        String pensionUrl = "harel-group.co.il/long-term-savings/pension/join/Pages/join-pension.aspx";

        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='קרנות פנסיה']")).click();
        driver.findElement(By.xpath("//a[@title='להצטרפות עכשיו']")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(pensionUrl));
    }

    @Test
    public void TestSearchInputWorks() {
        driver.get(harelHomeUrl);
        driver.findElement(By.id("searchBox")).sendKeys("abc");
        driver.findElement(By.xpath("//a[@title='חיפוש']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("SearchPage"));
    }

    @Test
    public void TestLoginIdErrorShowing() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuArrow']")).click();
        driver.findElement(By.id("idUser")).sendKeys("abc");
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuLogin']")).click();
        Assert.assertTrue(driver.findElement(By.id("idUser-helper-text")).isDisplayed());
    }

    @Test
    public void TestLoginPhoneErrorShowing() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuArrow']")).click();
        driver.findElement(By.id("phone")).sendKeys("abc");
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuLogin']")).click();
        Assert.assertTrue(driver.findElement(By.id("phone-helper-text")).isDisplayed());
    }

    @Test
    public void TestEmptyLoginErrorShowing() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuArrow']")).click();
        driver.findElement(By.xpath("//button[@data-hrl-bo='atm-personalMenuLogin']")).click();
        Assert.assertTrue(driver.findElement(By.id("idUser-helper-text")).isDisplayed() && driver.findElement(By.id("phone-helper-text")).isDisplayed());
    }

    @Test
    public void TestCsrPageReachable() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='אחריות תאגידית']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("CSR"));
    }

    @Test
    public void TestSearchPageReachable() {
        String searchPageUrl = "https://www.harel-group.co.il/Search/Pages/formlocator.aspx";

        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='לפרטים נוספים אודות ביטוח דירה לטווח קצר עבור רוכשי ביטוח הנסיעות לחו\"ל של הראל ']")).click();
        driver.findElement(By.xpath("//a[@title='איתור טפסים']")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(searchPageUrl, currentUrl);
    }

    @Test
    public void TestSurgeonsPageReachable() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='כניסה לרופאים']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("surgeons"));
    }

    @Test
    public void TestAboutPageReachable() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//a[@title='אודות הראל']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("about"));
    }

    @AfterClass
    public void afterTest() {
        try {
            driver.close();
        }
        catch (Exception e) {

        }
    }
}
