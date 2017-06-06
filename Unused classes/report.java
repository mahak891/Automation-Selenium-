import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by innoraft on 25/5/17.
 */

@Listeners(form_listener.class)
public class report {
    @Test
    public void testSimulation0() {
        Assert.assertTrue(true);
    }
    @Test
    public void testSimulation00() {
        Assert.assertTrue(false);
    }
    // Test case <testSimulation000> depends on the intentionally
// failed test case <testSimulation00>
    @Test(dependsOnMethods = "testSimulation00")
    public void testSimulation000() {
    }
}
