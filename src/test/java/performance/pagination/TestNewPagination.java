package performance.pagination;

import org.junit.Test;
import performance.TestBase;


public class TestNewPagination extends TestBase {

    @Test
    public void testPerformance() {
        runTest(PaginationTestCases.allTestCases, "new_master", "paginationnew", false);
    }
}
