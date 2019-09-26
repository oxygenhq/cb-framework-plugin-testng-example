package Unit;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(io.cloudbeat.testng.Plugin.class)
public class UnitTest {

    @Test(groups = {"success"})
    public void successEqualityTest() {
        Assert.assertEquals(3, 1+2);
    }

    @Test(groups = {"fail"})
    public void failEqualityTest() {
        Assert.assertEquals(3, 1+1);
    }
}
