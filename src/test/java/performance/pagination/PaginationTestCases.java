package performance.pagination;

import performance.TestCase;

import java.util.List;

public class PaginationTestCases {
    public static TestCase subseq1 = new TestCase(
            "subsequence1",
            "old_master",
            List.of(
                "subsequence(1 to 1000000000, 20*1000000-10, 10)",
                "subsequence(1 to 1000000000, 40*1000000-10, 10)",
                "subsequence(1 to 1000000000, 80*1000000-10, 10)",
                "subsequence(1 to 1000000000, 160*1000000-10, 10)",
                "subsequence(1 to 1000000000, 320*1000000-10, 10)",
                "subsequence(1 to 1000000000, 640*1000000-10, 10)",
                "subsequence(1 to 1000000000, 1280*1000000-10, 10)",
                "subsequence(1 to 1000000000, 2560*1000000-10, 10)"
            )
    );

    public static TestCase seqlook1 = new TestCase(
            "sequencelookup1",
            "old_master",
            List.of(
                "(1 to 1000000000)[4 * 1000000]",
                "(1 to 1000000000)[8 * 1000000]",
                "(1 to 1000000000)[16 * 1000000]",
                "(1 to 1000000000)[32 * 1000000]",
                "(1 to 1000000000)[64 * 1000000]",
                "(1 to 1000000000)[128 * 1000000]",
                "(1 to 1000000000)[256 * 1000000]",
                "(1 to 1000000000)[512 * 1000000]"
            )
    );

    public static TestCase subseq2 = new TestCase(
            "subsequence2",
            "old_master",
            List.of(
                "subsequence(parquet-file(\"performance_test_data/100062.parquet\"), 1 * 1000, 10)",
                "subsequence(parquet-file(\"performance_test_data/100062.parquet\"), 2 * 1000, 10)",
                "subsequence(parquet-file(\"performance_test_data/100062.parquet\"), 4 * 1000, 10)",
                "subsequence(parquet-file(\"performance_test_data/100062.parquet\"), 8 * 1000, 10)",
                "subsequence(parquet-file(\"performance_test_data/100062.parquet\"), 16 * 1000, 10)"
            )
    );

    public static TestCase seqlook2 = new TestCase(
            "sequencelookup2",
            "old_master",
            List.of(
                "parquet-file(\"performance_test_data/100062.parquet\")[1 * 1000]",
                "parquet-file(\"performance_test_data/100062.parquet\")[2 * 1000]",
                "parquet-file(\"performance_test_data/100062.parquet\")[4 * 1000]",
                "parquet-file(\"performance_test_data/100062.parquet\")[8 * 1000]",
                "parquet-file(\"performance_test_data/100062.parquet\")[16 * 1000]"
            )
    );

    public static List<TestCase> allTestCases = List.of(subseq1, subseq2, seqlook1, seqlook2);
}
