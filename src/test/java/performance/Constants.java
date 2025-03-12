package performance;

import java.util.List;

public class Constants {

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
                "subsequence(parquet-file(\"data/100062.parquet\"), 1 * 1000, 10)",
                "subsequence(parquet-file(\"data/100062.parquet\"), 2 * 1000, 10)",
                "subsequence(parquet-file(\"data/100062.parquet\"), 4 * 1000, 10)",
                "subsequence(parquet-file(\"data/100062.parquet\"), 8 * 1000, 10)",
                "subsequence(parquet-file(\"data/100062.parquet\"), 16 * 1000, 10)"
            )
    );

    public static TestCase seqlook2 = new TestCase(
            "sequencelookup2",
            "old_master",
            List.of(
                "parquet-file(\"data/100062.parquet\")[1 * 1000]",
                "parquet-file(\"data/100062.parquet\")[2 * 1000]",
                "parquet-file(\"data/100062.parquet\")[4 * 1000]",
                "parquet-file(\"data/100062.parquet\")[8 * 1000]",
                "parquet-file(\"data/100062.parquet\")[16 * 1000]"
            )
    );

    public static TestCase steps1r = new TestCase(
            "steps1",
            "rumble",
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols4k/*.xml\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps1s = new TestCase(
            "steps1",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols1k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols2k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols4k\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps2r = new TestCase(
            "steps2",
            "rumble",
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols2k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols4k/*.xml\")/descendant::date"

            )
    );

    public static TestCase steps2s = new TestCase(
            "steps2",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols1k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols10k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols20k\")/descendant::date"
            )
    );

    // part 1: subsequence and lookup
    public static List<TestCase> toTest = List.of(seqlook2, subseq2);

    // part 2: parallel steps
    // public static List<TestCase> toTest = List.of(steps1s, steps2s, steps1r, steps2r);
}
