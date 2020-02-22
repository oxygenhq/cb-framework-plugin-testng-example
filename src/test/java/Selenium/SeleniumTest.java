package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.CbTestNg {

    private String harelHomeUrl = "https://travel.harel-group.co.il/abroad-policy/";

    @BeforeClass
    public void initTest() throws Exception {
        WebDriver driver = createWebDriverBasedOnCbCapabilities();
        setWebDriver(driver);
    }

    @Test(groups = {"No", "Success"})
    public void TestEmptyToDateError() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();

        driver.findElement(By.id("travellers")).click();

        driver.findElement(By.id("firstName1")).sendKeys("abc");
        driver.findElement(By.id("lastName1")).sendKeys("dvq");
        driver.findElement(By.xpath("//label[@for='gender1-1']")).click();
        driver.findElement(By.id("dob1")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@data-hrl-bo='modal-travellers-confirm']")).click();

        new Select(driver.findElement(By.id("destination"))).selectByIndex(1);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();
        Assert.assertTrue(driver.findElement(By.id("todate-error-span")).isDisplayed());
    }

    @Test(groups = {"No", "Success"})
    public void TestEmptyFromDateError() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();

        driver.findElement(By.id("travellers")).click();

        driver.findElement(By.id("firstName1")).sendKeys("abc");
        driver.findElement(By.id("lastName1")).sendKeys("dvq");
        driver.findElement(By.xpath("//label[@for='gender1-1']")).click();
        driver.findElement(By.id("dob1")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@data-hrl-bo='modal-travellers-confirm']")).click();

        new Select(driver.findElement(By.id("destination"))).selectByIndex(1);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();
        Assert.assertTrue(driver.findElement(By.id("fromdate-error-span")).isDisplayed());
    }

    @Test(groups = {"No", "Success"})
    public void TestEmptyFirstNameError() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();
        driver.findElement(By.id("travellers")).click();
        driver.findElement(By.id("lastName1")).sendKeys("dvq");
        driver.findElement(By.xpath("//label[@for='gender1-1']")).click();
        driver.findElement(By.id("dob1")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@data-hrl-bo='modal-travellers-confirm']")).click();
        Assert.assertTrue(driver.findElement(By.id("travelerfirstnameerror1")).isDisplayed());
    }

    @Test(groups = {"No", "Success"})
    public void TestEmptyLastNameError() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();
        driver.findElement(By.id("travellers")).click();
        driver.findElement(By.id("firstName1")).sendKeys("dvq");
        driver.findElement(By.xpath("//label[@for='gender1-1']")).click();
        driver.findElement(By.id("dob1")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@data-hrl-bo='modal-travellers-confirm']")).click();
        Assert.assertTrue(driver.findElement(By.id("travelerlastnameerror1")).isDisplayed());
    }

    @Test(groups = {"No", "Success"})
    public void TestInfoShown() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();
        driver.findElement(By.xpath("//a[@data-hrl-bo='cover-1-info']")).click();
        Assert.assertTrue(driver.findElement(By.className("hsg-modal-dialog")).isDisplayed());
    }

    @Test(groups = {"Yes", "Success"})
    public void TestExistUserEmptyPhoneError() {
        driver.get(harelHomeUrl);
        WebElement yesButton = driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerYes']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", yesButton);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continue']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-hrl-bo='returnCustomerDataMobilePhonesuffixError']")).isDisplayed());
    }

    @Test(groups = {"Yes", "Success"})
    public void TestExistUserEmptyIdError() {
        driver.get(harelHomeUrl);
        WebElement yesButton = driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerYes']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", yesButton);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continue']")).click();
        Assert.assertTrue(driver.findElement(By.id("id-error-text")).isDisplayed());
    }

    @Test(groups = {"Yes", "Success"})
    public void TestStartPageFromYesCondition() {
        driver.get(harelHomeUrl);
        WebElement yesButton = driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerYes']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", yesButton);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueNoIdentify']")).click();
    }

    @Test(groups = {"No", "Success"})
    public void TestInsuranceEnabled() {
        driver.get(harelHomeUrl);
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div[2]/div/ul/li[1]/label")).isEnabled());
    }

    @Test(groups = {"No", "Fail","OnlyMe"})
    public void TestContinueWithoutDate() {
        driver.get(harelHomeUrl);
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")));
        driver.findElement(By.xpath("//button[@data-hrl-bo='returnCustomerQuestionAnswerNo']")).click();        
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("travellers")));
        driver.findElement(By.id("travellers")).click();

        driver.findElement(By.id("firstName1")).sendKeys("abc");
        driver.findElement(By.id("lastName1")).sendKeys("dvq");
        driver.findElement(By.xpath("//label[@for='gender1-1']")).click();
        driver.findElement(By.id("dob1")).sendKeys("111111");
        driver.findElement(By.xpath("//button[@data-hrl-bo='modal-travellers-confirm']")).click();
        new Select(driver.findElement(By.id("destination"))).selectByIndex(1);
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();

        driver.findElement(By.xpath("//label[@data-hrl-bo='primary-questions-answer-label-0-1']")).click();
        driver.findElement(By.xpath("//label[@data-hrl-bo='primary-questions-answer-label-1-0']")).click();
        driver.findElement(By.xpath("//label[@data-hrl-bo='primary-questions-answer-label-2-0']")).click();
        driver.findElement(By.xpath("//label[@data-hrl-bo='primary-questions-answer-label-3-0']")).click();
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();

        driver.findElement(By.xpath("//label[@data-hrl-bo='secondary-questions-answer-label-0-0-0']")).click();
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();
        driver.findElement(By.xpath("//label[@data-hrl-bo='secondary-questions-answer-label-0-0-0']")).click();
        driver.findElement(By.xpath("//button[@data-hrl-bo='continueButton']")).click();

        driver.findElement(By.xpath("//label[@data-hrl-bo='modalAttentionFinishButton']")).click();
        Assert.assertTrue(driver.findElement(By.id("todate-error-span")).isDisplayed());
    }

    @AfterClass
    public void afterTest() {
        try {
            driver.quit();
            driver.close();
        }
        catch (Exception e) {

        }
    }
}
