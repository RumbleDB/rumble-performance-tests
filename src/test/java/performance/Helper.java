package performance;

import net.sf.saxon.s9api.*;
import org.apache.spark.api.java.JavaRDD;
import org.rumbledb.api.Item;
import org.rumbledb.api.Rumble;
import org.rumbledb.api.SequenceOfItems;
import org.rumbledb.config.RumbleRuntimeConfiguration;
import sparksoniq.spark.SparkSessionManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static Rumble getRumble(String runtimeConfig) {
        List<String> args = new ArrayList<>();
        args.add("--output-format");
        args.add("json");
        args.add("--materialization-cap");
        args.add("1000000000");
        args.addAll(RuntimeConfig.lookup(runtimeConfig).args);
        return new Rumble(
                new RumbleRuntimeConfiguration(
                        args.toArray(new String[0])
                )
        );
    }

    public static long timeQueryRumble(String query, String runtimeConfig) {
        return timeQueryRumble(query, getRumble(runtimeConfig));
    }

    public static long timeQueryRumble(String query, Rumble rumble) {
        try {
            long startTime = System.currentTimeMillis();
            List<Item> items = new ArrayList<>();
            System.out.println("Testing: " + query);
            SequenceOfItems result = rumble.runQuery(query);
            if (!result.availableAsRDD()) {
                result.populateList(items);
            } else {
                JavaRDD<Item> rdd = result.getAsRDD();
                SparkSessionManager.collectRDDwithLimitWarningOnly(rdd, items);
            }
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("Result is" + items.stream().map(Item::serialize).collect(Collectors.toList()));
            return totalTime;
        } catch (OutOfMemoryError | Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public static XQueryCompiler getSaxon(String baseUri) {
        Processor proc = new Processor(false);
        XQueryCompiler xqc = proc.newXQueryCompiler();
        try {
            xqc.setBaseURI(new URI(baseUri));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return xqc;
    }

    public static long timeQuerySaxon(String query, String baseUri) {
        return timeQuerySaxon(query, getSaxon(baseUri));
    }

    public static long timeQuerySaxon(String query, XQueryCompiler xqc) {
        long startTime = System.currentTimeMillis();
        List<XdmItem> items = new ArrayList<>();
        XdmDestination destination = new XdmDestination();
        try {
            xqc.compile(query).load().run(destination);
        } catch (SaxonApiException e) {
            throw new RuntimeException(e);
        }
        XdmValue result = destination.getXdmNode();
        for (XdmItem item : result) {
            items.add(item);
        }
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Result is" + items.stream().map(XdmItem::getStringValue).collect(Collectors.toList()));
        return totalTime;
    }
}
