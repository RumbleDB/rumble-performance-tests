package performance.steps;

import org.junit.Test;
import performance.TestBase;


public class TestSteps extends TestBase {

    @Test
    public void testMaster1() {
        runTest(StepsTestCases.allSaxonCases, "saxon", true);
        runTest(StepsTestCases.allRumbleCases, "rumble", true);
    }

    @Test
    public void testMaster2() {
        runTest(StepsTestCases.allRumbleCases, "rumble_improved", true);
    }
}
