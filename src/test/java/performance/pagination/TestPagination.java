package performance.pagination;

import org.junit.Test;
import performance.TestBase;


public class TestPagination extends TestBase {

    @Test
    public void testMaster2() {
        runTest(PaginationTestCases.allTestCases, "final_master", "paginationfinal", false);
    }

    @Test
    public void testMaster1() {
        runTest(PaginationTestCases.allTestCases, "new_master", "paginationnew", false);
    }

    @Test
    public void testOldMaster() {
        runTest(PaginationTestCases.allTestCases, "old_master", "paginationold", false);
    }

}
