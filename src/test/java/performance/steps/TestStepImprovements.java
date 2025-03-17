package performance.steps;

import org.junit.Test;
import performance.TestBase;

public class TestStepImprovements extends TestBase {

    @Test
    public void testMaster2() {
        runTest(StepsTestCases.rumbleProtocols1, "opt_instanceof", "opt_instanceof", false);
        runTest(StepsTestCases.rumbleProtocols1, "opt_instanceof_parent", "opt_instanceof_parent", false);
        runTest(StepsTestCases.rumbleProtocols1, "opt_instanceof_parent_steps", "opt_instanceof_parent_steps", false);
    }

    @Test
    public void testMaster1() {
        runTest(StepsTestCases.rumbleProtocols1, "first_implementation", "first_implementation", false);
    }
}
