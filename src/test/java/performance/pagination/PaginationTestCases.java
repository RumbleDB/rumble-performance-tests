package performance.pagination;

import performance.TestCase;

import java.util.List;

public class PaginationTestCases {
    public static TestCase subseq1 = new TestCase(
            "subsequence1",
            "old_master",
            List.of(
                "subsequence(1 to 1000000020, 100000, 10)",
                "subsequence(1 to 1000000020, 1000000, 10)",
                "subsequence(1 to 1000000020, 10000000, 10)",
                "subsequence(1 to 1000000020, 100000000, 10)",
                "subsequence(1 to 1000000020, 1000000000, 10)"
            )
    );

    public static TestCase seqlook1 = new TestCase(
            "sequencelookup1",
            "old_master",
            List.of(
                "(1 to 1000000020)[100000]",
                "(1 to 1000000020)[1000000]",
                "(1 to 1000000020)[10000000]",
                "(1 to 1000000020)[100000000]",
                "(1 to 1000000020)[1000000000]"
            )
    );

    public static TestCase subseq2 = new TestCase(
            "subsequence2",
            "old_master",
            List.of(
                "subsequence(parquet-file(\"performance_test_data/flights.parquet\"), 500, 10)",
                "subsequence(parquet-file(\"performance_test_data/flights.parquet\"), 5000, 10)",
                "subsequence(parquet-file(\"performance_test_data/flights.parquet\"), 50000, 10)",
                "subsequence(parquet-file(\"performance_test_data/flights.parquet\"), 500000, 10)",
                "subsequence(parquet-file(\"performance_test_data/flights.parquet\"), 5000000, 10)"
            )
    );

    public static TestCase seqlook2 = new TestCase(
            "sequencelookup2",
            "old_master",
            List.of(
                "parquet-file(\"performance_test_data/flights.parquet\")[500]",
                "parquet-file(\"performance_test_data/flights.parquet\")[5000]",
                "parquet-file(\"performance_test_data/flights.parquet\")[50000]",
                "parquet-file(\"performance_test_data/flights.parquet\")[500000]",
                "parquet-file(\"performance_test_data/flights.parquet\")[5000000]"
            )
    );

    public static List<TestCase> allTestCases = List.of(subseq1, subseq2, seqlook1, seqlook2);
}
