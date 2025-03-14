package performance.pagination;

import org.junit.Test;
import performance.TestBase;


public class TestFinalPagination extends TestBase {

    @Test
    public void testPerformance() {
        runTest(PaginationTestCases.allTestCases, "final_master", "paginationfinal", false);
    }
}
