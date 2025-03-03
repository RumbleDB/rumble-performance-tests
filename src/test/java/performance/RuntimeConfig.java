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
        configs.put("old_master", new RuntimeConfig(true, Collections.emptyList()));
        configs.put("new_master", new RuntimeConfig(true, Collections.emptyList()));
        configs.put(
            "rumble",
            new RuntimeConfig(
                    true,
                    List.of("--optimize-steps", "no", "--optimize-parent-pointers", "no", "--optimize-instanceof", "no")
            )
        );
        configs.put(
            "rumble_improved",
            new RuntimeConfig(
                    true,
                    List.of(
                        "--optimize-steps",
                        "yes",
                        "--optimize-parent-pointers",
                        "yes",
                        "--optimize-instanceof",
                        "yes"
                    )
            )
        );
    }

    public static RuntimeConfig lookup(String lookup) {
        return configs.get(lookup);
    }
}
