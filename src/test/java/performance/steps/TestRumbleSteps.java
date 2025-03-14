package performance.steps;

import org.junit.Test;
import performance.TestBase;


public class TestRumbleSteps extends TestBase {

    @Test
    public void testPerformance() {
        runTest(StepsTestCases.allRumbleCases, "rumble", "stepsrumble", true);
    }
}
