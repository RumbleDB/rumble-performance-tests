package scaling.steps;

import org.junit.Test;
import scaling.ScalingTest;


public class TestSteps extends ScalingTest {

    @Test
    public void testMaster1() {
        runTest(StepsTestCases.allSaxonCases, "saxon");
        runTest(StepsTestCases.allRumbleNoEdgar, "rumble");
        // runTest(StepsTestCases.allRumbleCases, "rumble"); edgar fail and take forever
    }

    @Test
    public void testMaster2() {
        runTest(StepsTestCases.allRumbleCases, "rumble_experimental");
        runTest(StepsTestCases.allRumbleCases, "rumble_improved");
    }

    @Override
    public String getTestName() {
        return "Steps";
    }

    @Override
    public boolean getInitTesting() {
        return true;
    }
}
