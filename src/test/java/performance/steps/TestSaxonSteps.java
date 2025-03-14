package performance.steps;

import org.junit.Test;
import performance.TestBase;


public class TestSaxonSteps extends TestBase {

    @Test
    public void testPerformance() {
        runTest(StepsTestCases.allSaxonCases, "saxon", "stepssaxon", true);
    }
}
