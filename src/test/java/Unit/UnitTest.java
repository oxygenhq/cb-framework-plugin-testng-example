package Unit;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class UnitTest {

    @Test(groups = {"success", "unit"})
    public void successEqualityTest() {
        Assert.assertEquals(4, 2+2);
    }

    @Test(groups = {"fail", "unit"})
    public void failEqualityTest() {
        Assert.assertEquals(3, 1+1);
    }

}
