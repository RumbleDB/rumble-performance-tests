import itertools
import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

plot_lookup = {
    "subsequence1": ("subsequence(1 to 1e9+20, X, 10)", ["1e5", "1e6", "1e7", "1e8", "1e9"]),
    "sequencelookup1": ("(1 to 1e9+20)[X]", ["1e5", "1e6", "1e7", "1e8", "1e9"]),
    "subsequence2": ("subsequence(parquet-file(\"performance_test_data/flights.parquet\"), X, 10)", ["500", "5000", "50000", "500000", "5000000"]),
    "sequencelookup2": ("parquet-file(\"performance_test_data/flights.parquet\")[X]", ["500", "5000", "50000", "500000", "5000000"]),

    "steps_protocols_1": ("xml-files(\"./protocolsX/*.xml\")/TEI/text/body/p[2]", ["1k", "2k", "4k", "8k", "16k"]),
    "steps_protocols_2": ("xml-files(\"./protocolsX/*.xml\")/descendant::date", ["1k", "2k", "4k", "8k", "16k"]),
    "steps_edgar1": ("xml-files(\"./performance_test_data/edgarX/*.xml\")/xbrl/context/entity/identifier", ["16", "64", "256", "1024"]),
    "steps_edgar2": ("xml-files(\"./performance_test_data/edgarX/*.xml\")/descendant::segment", ["16", "64", "256", "1024"]),

    "steps_improvements": ("xml-files(\"./protocolsX/*.xml\")/TEI/text/body/p[2]", ["1k", "2k", "4k", "8k", "16k"]),

    "cluster": ("xml-files(edgarX, 20)/xbrl/context/entity/identifier", ["64", "256", "1024", "4096", "16384"]),
}

color_lookup = {
    "saxon": "blue",
    "rumble": "orange",
    "rumble_improved": "green",
    "rumble_experimental": "red",

    "old_master": "blue",
    "new_master": "orange",

    "first_implementation": "orange",
    "opt_instanceof_parent_steps": "green",
    "opt_instanceof_parent": "blue",
    "opt_instanceof": "purple",
}

ordered_configs = [
    "old_master",
    "new_master",
    "first_implementation",
    "rumble",
    "opt_instanceof",
    "opt_instanceof_parent",
    "opt_instanceof_parent_steps",
    "rumble_improved",
    "rumble_experimental",
    "saxon"
]

skipped = [
    #"rumble_experimental",
    #"rumble_improved",

    #"opt_instanceof_parent_steps",
    #"opt_instanceof_parent",
]

def load_data(path):
    return pd.concat([pd.read_json(os.path.join(path, f), lines=True) for f in os.listdir(path)])

def agg_runtime(g):
    if (g.runtime >= 0).all():
        return pd.Series({'mean': g.runtime.mean(), 'std': g.runtime.std(), 'failed': False})
    return pd.Series({'mean': 0, 'std': 0, 'failed': True})

def make_plot(data, title):
    plt.style.use('seaborn-v0_8-whitegrid')
    fig, ax = plt.subplots(figsize=(14, 8))
    #ax.set_yscale('symlog')

    order = data['queryIndex'].drop_duplicates().sort_values()
    grouped = data.groupby(['queryIndex', 'configName']).apply(agg_runtime).reset_index()
    pivot = grouped.pivot(index='queryIndex', columns='configName').loc[order]

    x = np.arange(len(pivot.index))
    configs = pivot['mean'].columns.tolist()
    marker = itertools.cycle(('o', '^', 's', '*', '+'))
    legend_labels = []
    for config in ordered_configs:
        if config in skipped or config not in configs:
            continue
        valid_mask = ~pivot['failed'][config]
        if valid_mask.sum() == 0:
            continue

        x_valid = x[valid_mask]
        means_valid = (pivot['mean'][config] / 1000)[valid_mask]
        stds_valid = (pivot['std'][config] / 1000)[valid_mask]

        color = color_lookup[config]
        err = ax.errorbar(x_valid, means_valid, yerr=stds_valid, marker=next(marker), linestyle='-', linewidth=2, markersize=7, capsize=5, label=config, elinewidth=2, color=color)

        config_init = data[data['configName'] == config]['estimatedInit'].mean() / 1000
        if config_init > 0:
            #ax.axhline(y=config_init, color=color, linestyle='--', linewidth=2, alpha=0.8, label=None)
            config_label = f"{config} (est. init: {config_init:.2f} s)"
            legend_labels.append(config_label)
        else:
            config_label = f"{config} (est. init: 0 s)"
            legend_labels.append(config)

    ax.set_xlabel('Query', fontsize=21, fontweight='bold')
    ax.set_ylabel('Runtime (s)', fontsize=21, fontweight='bold')
    ax.set_title(plot_lookup[title][0], fontsize=24, fontweight='bold')
    ax.set_xticks(x)
    ax.set_xticklabels(plot_lookup[title][1])
    ax.tick_params(labelsize=18)
    ax.set_ylim(bottom=0)
    handles, _ = ax.get_legend_handles_labels()
    ax.legend(fontsize=18, frameon=True, shadow=True, handles=handles[:], labels=legend_labels+legend_labels)

    plt.tight_layout()
    os.makedirs("plots", exist_ok=True)
    plt.savefig(f"plots/{title}.svg")
    plt.savefig(f"plots/{title}.png")
    plt.show()

result_path = "./scaling-results"
big_df = load_data(result_path)

for testName in big_df['testName'].unique():
    make_plot(big_df[big_df['testName'] == testName], testName)