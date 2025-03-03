package performance;

import net.sf.saxon.s9api.*;
import org.rumbledb.api.Rumble;


public class InitTimeEstimator {

    public static void main(String[] args) {
        String query = args[0];
        String runtimeConfig = args[1];
        String baseUri = args[2];

        long timeDifference;
        if (RuntimeConfig.lookup(runtimeConfig).useRumble) {
            Rumble rumble = Helper.getRumble(runtimeConfig);
            long time1 = Helper.timeQueryRumble(query, rumble);
            long time2 = Helper.timeQueryRumble(query, rumble);
            timeDifference = time1 - time2;
        } else {
            XQueryCompiler xqc = Helper.getSaxon(baseUri);
            long time1 = Helper.timeQuerySaxon(query, xqc);
            long time2 = Helper.timeQuerySaxon(query, xqc);
            timeDifference = time1 - time2;
        }
        System.out.println("Result:" + timeDifference);
    }

}
