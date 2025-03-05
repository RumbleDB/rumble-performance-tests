package performance.steps;

import performance.TestCase;
import java.util.List;

public class StepsTestCases {
    public static TestCase steps_protocols_1r = new TestCase(
            "steps_protocols_1",
            "rumble",
            List.of(
                "xml-files(\"./performance_test_data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./performance_test_data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps_protocols_1s = new TestCase(
            "steps_protocols_1",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols1k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols2k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols4k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols8k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols16k\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps_protocols_2r = new TestCase(
            "steps_protocols_2",
            "rumble",
            List.of(
                "xml-files(\"./performance_test_data/protocols1k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols2k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols4k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols8k/*.xml\")/descendant::date",
                "xml-files(\"./performance_test_data/protocols16k/*.xml\")/descendant::date"

            )
    );

    public static TestCase steps_protocols_2s = new TestCase(
            "steps_protocols_2",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols1k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols2k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols4k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols8k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"performance_test_data/protocols16k\")/descendant::date"
            )
    );

    public static TestCase steps_edgar1r = new TestCase(
            "steps_edgar1",
            "rumble",
            List.of(
                "xml-files(\"../performance_test_data/edgar16/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"../performance_test_data/edgar64/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"../performance_test_data/edgar256/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"../performance_test_data/edgar1024/*.xml\")/xbrl/context/entity/identifier"

            )
    );

    public static TestCase steps_edgar1s = new TestCase(
            "steps_edgar1",
            "saxon",
            List.of(
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./performance_test_data/edgar16\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./performance_test_data/edgar64\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./performance_test_data/edgar256\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./performance_test_data/edgar1024\")/xbrl/context/entity/identifier"
            )
    );
    public static List<TestCase> allRumbleCases = List.of(steps_edgar1r);
    public static List<TestCase> allSaxonCases = List.of(steps_edgar1s);
    // public static List<TestCase> allRumbleCases = List.of(steps_edgar1r, steps_protocols_1r, steps_protocols_2r);
    // public static List<TestCase> allSaxonCases = List.of(steps_edgar1s, steps_protocols_1s, steps_protocols_2s);
}
