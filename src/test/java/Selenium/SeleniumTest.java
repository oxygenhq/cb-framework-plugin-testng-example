package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasProperty;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class SeleniumTest extends io.cloudbeat.testng.CbTestNg {

    private String homeUrl = "http://automationpractice.com/index.php";

    @BeforeClass
    public void initTest() throws Exception {
        setupTest();
        setupWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test(groups = {"API", "Sanity"}, description = "This is weather service API test.")
    public void weatherApiTest() {
        given()
                .when()
                .get("https://api.weather.gov/points/39.7456,-97.0892")
                .then()
                .statusCode(200)
                .and()
                .body("geometry", hasProperty("type"));
        //body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }

    @Test(groups = {"success", "purchase"})
    public void purchaseDress() {
        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Select Dresses");
        driver.findElement(By.linkText("DRESSES")).click();
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

        startStep("Open the web site");
        driver.navigate().to(homeUrl);
        endStep("Open the web site");

        startStep("Select T-SHIRTS");
        driver.findElement(By.linkText("T-SHIRTS")).click();
        endStep("Select T-SHIRTS");

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
        startStep("Open the web site");
        driver.navigate().to(homeUrl);
        endStep("Open the web site");

        startStep("Select Dresses");
        driver.findElement(By.linkText("DRESSES")).click();
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
        startStep("Open the web site");
        driver.navigate().to(homeUrl);
        endStep("Open the web site");

        startStep("Select Women clothing");
        driver.findElement(By.linkText("WOMEN")).click();
        endStep("Select Women clothing");

        startStep("Select Tops");
        driver.findElement(By.id("layered_category_4")).click();
        endStep("Select Tops");

        startStep("Select Black");
        driver.findElement(By.linkText("Black (2)")).click();
        endStep("Select Black");

        startStep("Select Cotton");
        driver.findElement(By.id("layered_id_feature_5")).click();
        endStep("Select Cotton");

        startStep("Open Specials");
        driver.findElement(By.xpath("//div[@id='special_block_right']/div/div/a/span")).click();
        endStep("Open Specials");
    }

    @Test(groups = {"success"})
    public void addToCartAndCheckout() throws InterruptedException {
        startStep("Open the web site");
        driver.navigate().to(homeUrl);
        // maximize browser window to omit screen resolution related issues
        driver.manage().window().maximize();
        endStep("Open the web site");

        startStep("Select a T-Shirt");

        startStep("Click on T-SHIRTS menu");
        driver.findElement(By.linkText("T-SHIRTS")).click();
        endStep("Click on T-SHIRTS menu");

        startStep("Find and select a first t-shirt in the list");
        List<WebElement> tshirtsList = driver.findElements(By.cssSelector(".product-image-container > .product_img_link"));
        Assert.assertTrue(tshirtsList.size() > 0, "T-Shirts list must not be empty");
        //driver.findElement(By.xpath("(//div[@id='center_column']/ul/li/div/div[2]/div[2]/a/span)[1]")).click();
        endStep("Find and select a first t-shirt in the list");
        startStep("Click on the first t-shirt");
        tshirtsList.get(0).click();
        endStep("Click on the first t-shirt");

        endStep("Select a T-Shirt");

        startStep("Add to cart");
        driver.findElement(By.name("Submit")).click();
        //driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/a/span")).click();
        endStep("Add to cart");

        startStep("Assert success message popup");
        WebElement addToCartSuccessMsgElm = driver.findElement(By.cssSelector(".layer_cart_product h2"));
        // wait for success popup to appear
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(addToCartSuccessMsgElm));
        // make sure the popup appeared after 60 sec max
        Assert.assertTrue(addToCartSuccessMsgElm.isDisplayed(), "Popup with successfully added message must be displayed");
        Assert.assertEquals(addToCartSuccessMsgElm.getText(), "Product successfully added to your shopping cart");
        endStep("Assert success message popup");

        startStep("Assert cart total is $18.51");
        // assert the current shopping cart sum
        WebElement currentTotalElm = driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']"));
        Assert.assertEquals(currentTotalElm.getText(), "$18.51");
        endStep("Assert cart total is $18.51");

        startStep("Click on Proceed to Checkout button");
        driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
        endStep("Click on Proceed to Checkout button");

        // by now, we suppose to be on Shopping Cart Summary page
        startStep("Checkout process");

        startStep("Assert that the current page is Shopping Cart Summary");
        WebElement cartTitleElm = driver.findElement(By.id("cart_title"));
        Assert.assertTrue(cartTitleElm.getText().contains("SHOPPING-CART SUMMARY"), "Page title must contain 'SHOPPING-CART SUMMARY'");
        endStep("Assert that the current page is Shopping Cart Summary");

        // If we refer to "Proceed to checkout" button using its title, then we will get ElementNotInteractableException
        // as multiple elements on the page match this criteria. Thus, we will use more precise css name-based xpath instead.

        driver.findElement(By.xpath("//a[contains(@class, 'standard-checkout')]")).click();
        //driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();

        startStep("Login with pre-registered user");
        //driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a/span")).click();
        driver.findElement(By.id("email")).sendKeys("erandd@yahoo.com");
        driver.findElement(By.id("passwd")).sendKeys("eran1234");
        driver.findElement(By.name("SubmitLogin")).click();
        startStep("Login with pre-registered user");

        startStep("Verify shipping");
        driver.findElement(By.xpath("//div[@id='center_column']/form/p/button/span")).click();
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.xpath("//form[@id='form']/p/button/span")).click();
        endStep("Verify shipping");

        startStep("Verify payment");
        driver.findElement(By.xpath("(//div[@id='HOOK_PAYMENT']/div/div/p/a/span)[1]")).click();
        driver.findElement(By.xpath("//p[@id='cart_navigation']/button/span")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='center_column']/h1")).getText(), "ORDER CONFIRMATION");
        endStep("Verify payment");

        endStep("Checkout process");
    }

    @Test(groups = {"fail"})
    public void addToCartFail() throws InterruptedException {
        startStep("Open web site");
        driver.navigate().to(homeUrl);
        endStep("Open web site");

        startStep("Purchase T shirt");

        startStep("Select T shirts");
        driver.findElement(By.linkText("T-SHIRTS")).click();
        endStep("Select T shirts");

        startStep("Click on T shirt");
        driver.findElement(By.xpath("(//div[@id='center_column']/ul/li/div/div[2]/div[2]/a/span)[1]")).click();
        endStep("Click on T shirt");

        endStep("Purchase T shirt");

        startStep("Add to cart");
        driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/a/span")).click();
        endStep("Add to cart");

        startStep("Change quantity to 2");
        driver.findElement(By.xpath("//a[2]/span/i")).click();
        endStep("Change quantity to 2");

        startStep("Verify price for 2 objects");
        synchronized (driver)
        {
            driver.wait(2000);
        }
        Assert.assertEquals(driver.findElement(By.id("total_price")).getText(), "$36.02");
        endStep("Verify price for 2 objects");

        startStep("Insert Personal details");
        driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a/span")).click();
        driver.findElement(By.id("email")).sendKeys("erandd@yahoo.com");
        driver.findElement(By.id("passwd")).sendKeys("eran1234");
        driver.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();
        endStep("Insert Personal details");

        startStep("Verify Shipping");
        driver.findElement(By.xpath("//div[@id='center_column']/form/p/button/span")).click();
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.xpath("//form[@id='form']/p/button/span")).click();
        endStep("Verify Shipping");

        startStep("Verify Payment");
        driver.findElement(By.xpath("(//div[@id='HOOK_PAYMENT']/div/div/p/a/span)[1]")).click();
        driver.findElement(By.xpath("//p[@id='cart_navigation']/button/span")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='center_column']/h1")).getText(), "ORDER CONFIRMATION");
        endStep("Verify Payment");
    }

    @AfterClass
    public void afterTest() {
        if(driver != null)
            driver.close();
    }
}
