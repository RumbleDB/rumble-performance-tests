package performance;

import java.util.List;

public class TestCase {
    String testName;
    List<String> queries;

    public TestCase(String testName, List<String> queries) {
        this.testName = testName;
        this.queries = queries;
    }
}
