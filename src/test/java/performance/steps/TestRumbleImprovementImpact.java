package performance.steps;

import org.junit.Test;
import performance.TestBase;

public class TestRumbleImprovementImpact extends TestBase {

    @Test
    public void testPerformance() {
        runTest(StepsTestCases.allRumbleCases, "rumble_improved", "stepsrumbleimproved", true);
    }
}
