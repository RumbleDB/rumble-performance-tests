package performance;

import java.util.List;

public class TestCase {
    String testName;
    String configName;
    List<String> queries;

    public TestCase(String testName, String configName, List<String> queries) {
        this.testName = testName;
        this.configName = configName;
        this.queries = queries;
    }

    public TestCase(String testName, List<String> queries) {
        this.testName = testName;
        this.queries = queries;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public TestCase copy() {
        return new TestCase(testName, configName, queries);
    }
}
