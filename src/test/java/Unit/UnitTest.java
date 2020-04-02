package Unit;

import io.cloudbeat.testng.CbTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class UnitTest extends CbTestNg {

    @Test(groups = {"success", "unit"})
    public void successEqualityTest() {
        startStep("Assertion");
        Assert.assertEquals(4, 2+2);
        endStep("Assertion");
    }

    @Test(groups = {"fail", "unit"})
    public void failEqualityTest() {
        startStep("Assertion");
        Assert.assertEquals(3, 1+1);
        endStep("Assertion");
    }

}
