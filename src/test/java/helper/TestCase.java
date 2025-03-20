package helper;

import java.util.List;

public class TestCase {
    String testName;
    List<String> queries;

    public TestCase(String testName, List<String> queries) {
        this.testName = testName;
        this.queries = queries;
    }

    public String getTestName() {
        return testName;
    }

    public List<String> getQueries() {
        return queries;
    }
}
