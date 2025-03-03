package performance.steps;

import org.junit.Test;
import performance.TestBase;
import performance.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRumbleImprovedSteps extends TestBase {

    @Test
    public void testPerformance() {
        List<TestCase> cases = new ArrayList<>();
        for (String configName : List.of("rumble_improved")) {
            for (TestCase testCase : StepsTestCases.allRumbleCases) {
                testCase = testCase.copy();
                testCase.setConfigName(configName);
                cases.add(testCase);
            }
        }
        runTest(cases);
    }
}
