# Introduction
This repo contains the codes used for my (Marco Schöb) master thesis to create the plots for the runtime analysis of my changes to rumbleDB.

# Steps to reproduce results
## Download and prepare data
1. Download the csv [here](https://data.sbb.ch/explore/dataset/ist-daten-sbb/export/) and convert it to xml using excel. Place the resulting file inside a new folder called 'sbbxml1' inside the performance_test_data folder.
2. Download [this](https://opendata.swiss/en/dataset/zurcher-stillstandsprotokolle-des-17-jahrhunderts) zip archive and rename it to 'protocols1k' and place it in the performance_test_data folder.
3. multiply data (WARNING: THIS NEEDS ABOUT 40GB OF FREE STORAGE)
```
cd performance_test_data
chmod +x ./copy_files_sbb.sh
./copy_files_sbb.sh

chmod +x ./copy_files_protocols.sh
./copy_files_protocols.sh
````

## Run performance tests
5. clone rumble
```
git clone http://gitlab.inf.ethz.ch/gfourny/rumble.git
```
6. run script that runs all tests
```
chmod +x ./run_all_tests.sh
./run_all_tests.sh
````

## Evaluate results
TODO