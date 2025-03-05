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


    public static long timeQueryRumble(String query, Rumble rumble, String runtimeConfig) {
        try {
            long startTime = System.currentTimeMillis();
            if (rumble == null) {
                rumble = getRumble(runtimeConfig);
            }
            List<Item> items = new ArrayList<>();
            System.out.println("Testing: " + query);
            SequenceOfItems result = rumble.runQuery(query);
            if (!result.availableAsRDD()) {
                result.populateList(items);
            } else {
                JavaRDD<Item> rdd = result.getAsRDD();
                SparkSessionManager.collectRDDwithLimitWarningOnly(rdd, items);
            }
            System.out.println("Result is" + items.stream().map(Item::serialize).collect(Collectors.toList()));
            long totalTime = System.currentTimeMillis() - startTime;
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


    public static long timeQuerySaxon(String query, XQueryCompiler xqc, String baseUri) {
        long startTime = System.currentTimeMillis();
        if (xqc == null) {
            xqc = getSaxon(baseUri);
        }
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
        System.out.println("Result is" + items.stream().map(XdmItem::getStringValue).collect(Collectors.toList()));
        long totalTime = System.currentTimeMillis() - startTime;
        return totalTime;
    }
}
