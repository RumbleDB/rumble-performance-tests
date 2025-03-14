package performance;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public void runTest(List<TestCase> testCases, String configName, String testName, boolean measureInit) {
        String outputName = java.time.LocalDateTime.now() + "_" + testName;
        for (int rep = 1; rep <= Config.reps; rep++) {
            for (TestCase test : testCases) {
                long estimatedInitTime = measureInit ? testInitTime(test.queries.get(0), configName) : -1;
                int queryIndex = 0;
                for (String query : test.queries) {
                    queryIndex++;
                    System.out.println("Testing: " + query);
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder(
                                "java",
                                "-cp",
                                System.getProperty("java.class.path"),
                                "performance.ExecutionTimer",
                                test.testName,
                                configName,
                                query,
                                String.valueOf(rep),
                                String.valueOf(queryIndex),
                                String.valueOf(estimatedInitTime),
                                outputName,
                                Paths.get("").toUri().toString()
                        );
                        processBuilder.inheritIO();
                        Process process = processBuilder.start();
                        try (
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))
                        ) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        }
                        process.waitFor(240, TimeUnit.SECONDS);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private long testInitTime(String query, String configName) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    "performance.InitTimeEstimator",
                    query,
                    configName,
                    Paths.get("").toUri().toString()
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.startsWith("Result:")) {
                        return Long.parseLong(line.split(":")[1].trim());
                    }
                }
            }
            process.waitFor(240, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
