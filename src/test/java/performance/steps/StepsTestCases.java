package performance.steps;

import performance.TestCase;
import java.util.List;

public class StepsTestCases {
    public static TestCase steps1r = new TestCase(
            "steps1",
            "rumble",
            List.of(
                "xml-files(\"./performance_test_data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps1s = new TestCase(
            "steps1",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols1k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols2k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols4k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols8k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols16k\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps2r = new TestCase(
            "steps2",
            "rumble",
            List.of(
                "xml-files(\"./performance_test_data/protocols1k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols2k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols4k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols8k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols16k/*.xml\")/descendant::date"

            )
    );

    public static TestCase steps2s = new TestCase(
            "steps2",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols1k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols2k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols4k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols8k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols16k\")/descendant::date"
            )
    );

    public static TestCase steps3r = new TestCase(
            "steps3",
            "rumble",
            List.of(
                "count(xml-files(\"./performance_test_data/sbbxml16/*.xml\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "count(xml-files(\"./performance_test_data/sbbxml64/*.xml\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "count(xml-files(\"./performance_test_data/sbbxml256/*.xml\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "count(xml-files(\"./performance_test_data/sbbxml1024/*.xml\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)"

            )
    );

    public static TestCase steps3s = new TestCase(
            "steps3",
            "saxon",
            List.of(
                "declare default element namespace \"urn:schemas-microsoft-com:office:spreadsheet\"; count(collection(\"performance_test_data/sbbxml16\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "declare default element namespace \"urn:schemas-microsoft-com:office:spreadsheet\"; count(collection(\"performance_test_data/sbbxml64\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "declare default element namespace \"urn:schemas-microsoft-com:office:spreadsheet\"; count(collection(\"performance_test_data/sbbxml256\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)",
                "declare default element namespace \"urn:schemas-microsoft-com:office:spreadsheet\"; count(collection(\"performance_test_data/sbbxml1024\")[2]/Workbook/Worksheet/Table/Row/Cell/Data)"
            )
    );

    public static List<TestCase> allRumbleCases = List.of(steps1r, steps2r, steps3r);
    public static List<TestCase> allSaxonCases = List.of(steps1s, steps2s, steps3s);
}
