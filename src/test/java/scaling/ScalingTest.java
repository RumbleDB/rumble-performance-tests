package scaling;


import helper.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class ScalingTest {

    public abstract String getTestName();

    public abstract boolean getInitTesting();

    public void runTest(List<TestCase> testCases, String configName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd'T'HHmmss");
        String outputName = getTestName() + "_" + java.time.LocalDateTime.now().format(formatter) + "_" + configName;
        for (int rep = 1; rep <= Config.reps; rep++) {
            for (TestCase test : testCases) {
                boolean measureInit = getInitTesting();
                long estimatedInitTime = measureInit ? testInitTime(test.getQueries().get(0), configName) : -1;
                int queryIndex = 0;
                for (String query : test.getQueries()) {
                    queryIndex++;
                    System.out.println("Testing: " + query);
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder(
                                "java",
                                "-cp",
                                System.getProperty("java.class.path"),
                                "helper.ExecutionTimer",
                                test.getTestName(),
                                configName,
                                query,
                                String.valueOf(rep),
                                String.valueOf(queryIndex),
                                String.valueOf(estimatedInitTime),
                                outputName
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
                    "helper.InitTimeEstimator",
                    query,
                    configName
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
