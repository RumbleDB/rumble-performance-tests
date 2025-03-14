package performance.pagination;

import org.junit.Test;
import performance.TestBase;


public class TestOldPagination extends TestBase {

    @Test
    public void testPerformance() {
        runTest(PaginationTestCases.allTestCases, "old_master", "paginationold", false);
    }
}
