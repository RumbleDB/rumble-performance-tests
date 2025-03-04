package performance.pagination;

import org.junit.Test;
import performance.TestBase;
import performance.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestOldPagination extends TestBase {

    @Test
    public void testPerformance() {
        List<TestCase> cases = new ArrayList<>();
        for (TestCase testCase : PaginationTestCases.allTestCases) {
            testCase = testCase.copy();
            testCase.setConfigName("old_master");
            cases.add(testCase);
        }
        runTest(cases, "paginationold");
    }
}
