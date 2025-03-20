package scaling.pagination;

import org.junit.Test;
import scaling.ScalingTest;


public class TestPagination extends ScalingTest {

    @Test
    public void testMaster2() {
        runTest(PaginationTestCases.allTestCases, "final_master");
    }

    @Test
    public void testMaster1() {
        runTest(PaginationTestCases.allTestCases, "new_master");
    }

    @Test
    public void testOldMaster() {
        runTest(PaginationTestCases.allTestCases, "old_master");
    }

    @Override
    public String getTestName() {
        return "Pagination";
    }

    @Override
    public boolean getInitTesting() {
        return false;
    }
}
