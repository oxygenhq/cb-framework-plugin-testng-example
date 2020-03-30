package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.CbTestNg {

    private String homeUrl = "http://automationpractice.com/index.php";

    @BeforeClass
    public void initTest() throws Exception {
        setupWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(groups = {"success", "purchase"})
    public void purchaseDress() {
        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Select Dresses");
        driver.findElement(By.xpath("//a[contains(text(),'Dresses')]")).click();
        endStep("Select Dresses");

        startStep("Select Size");
        driver.findElement(By.id("layered_category_11")).click();
        endStep("Select Size");

        startStep("Select Color");
        driver.findElement(By.linkText("Yellow (3)"));
        endStep("Select Color");

        startStep("Select Range");
        driver.findElement(By.id("layered_id_attribute_group_16")).click();
        endStep("Select Range");

        startStep("Open Specials");
        driver.findElement(By.xpath("//div[@id='special_block_right']/div/div/a/span")).click();
        endStep("Open Specials");
    }

    @Test(groups = {"success", "purchase"})
    public void purchaseTShirt() {

        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Select T shirts");
        driver.findElement(By.linkText("T-SHIRTS")).click();
        endStep("Select T shirts");

        startStep("Filter S");
        driver.findElement(By.id("layered_id_attribute_group_1")).click();
        endStep("Filter S");

        startStep("Select Orange");
        driver.findElement(By.linkText("Orange (1)")).click();
        endStep("Select Orange");

        startStep("Select Cotton");
        driver.findElement(By.id("layered_id_attribute_group_13"));
        driver.findElement(By.id("layered_id_feature_5"));
        endStep("Select Cotton");

        startStep("Open Specials");
        driver.findElement(By.xpath("//div[@id='special_block_right']/div/div/a/span")).click();
        endStep("Open Specials");
    }

    @Test(groups = {"fail"})
    public void failPurchaseDress() {
        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Select Dresses");
        driver.findElement(By.xpath("//a[contains(text(),'Dresses')]")).click();
        endStep("Select Dresses");

        startStep("Select Size");
        driver.findElement(By.id("layered_category_11")).click();
        endStep("Select Size");

        startStep("Select Color");
        driver.findElement(By.linkText("Yellow (3)"));
        endStep("Select Color");

        startStep("Select Range");
        driver.findElement(By.id("layered_id_attribute_group_116")).click();
        endStep("Select Range");
    }

    @Test(groups = {"purchase", "success"})
    public void purchaseWomenClothing() {
        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Select Women clothing");
        driver.findElement(By.linkText("WOMEN")).click();
        endStep("Select Women clothing");

        startStep("Select Tops");
        driver.findElement(By.id("layered_category_4")).click();
        endStep("Select Tops");

        startStep("Select black");
        driver.findElement(By.linkText("Black (2)")).click();
        endStep("Select black");

        startStep("Select cotton");
        driver.findElement(By.id("layered_id_feature_5'")).click();
        endStep("Select cotton");

        startStep("Open Specials");
        driver.findElement(By.xpath("//div[@id='special_block_right']/div/div/a/span")).click();
        endStep("Open Specials");
    }

    @AfterClass
    public void afterTest() {
        if(driver != null)
            driver.close();
    }
}
