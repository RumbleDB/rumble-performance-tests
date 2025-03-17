package performance;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuntimeConfig {
    public boolean useRumble;
    public List<String> args;

    private static Map<String, RuntimeConfig> configs = new HashMap<>();

    public RuntimeConfig(boolean useRumble, List<String> args) {
        this.useRumble = useRumble;
        this.args = args;
    }

    static {
        configs.put("saxon", new RuntimeConfig(false, Collections.emptyList()));
        configs.put(
            "opt_instanceof",
            new RuntimeConfig(
                    true,
                    List.of("--optimize-steps", "no", "--optimize-parent-pointers", "no")
            )
        );
        configs.put(
            "opt_instanceof_parent",
            new RuntimeConfig(
                    true,
                    List.of("--optimize-steps", "no")
            )
        );
    }

    public static RuntimeConfig lookup(String lookup) {
        if (configs.containsKey(lookup)) {
            return configs.get(lookup);
        } else {
            return new RuntimeConfig(true, Collections.emptyList());
        }
    }
}
