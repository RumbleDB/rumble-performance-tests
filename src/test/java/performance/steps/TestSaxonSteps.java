package performance.steps;

import org.junit.Test;
import performance.TestBase;
import performance.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestSaxonSteps extends TestBase {

    @Test
    public void testPerformance() {
        List<TestCase> cases = new ArrayList<>();
        for (TestCase testCase : StepsTestCases.allSaxonCases) {
            testCase = testCase.copy();
            testCase.setConfigName("saxon");
            cases.add(testCase);
        }
        runTest(cases);
    }
}
