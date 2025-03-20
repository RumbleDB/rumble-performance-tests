package scaling.steps;

import org.junit.Test;
import scaling.ScalingTest;

public class TestStepImprovements extends ScalingTest {

    @Test
    public void testMaster2() {
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof");
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof_parent");
        runTest(StepsTestCases.stepImprovementCases, "opt_instanceof_parent_steps");
    }

    @Test
    public void testMaster1() {
        runTest(StepsTestCases.stepImprovementCases, "first_implementation");
    }

    @Override
    public String getTestName() {
        return "StepImprovements";
    }

    @Override
    public boolean getInitTesting() {
        return false;
    }
}
