# Introduction
This repo contains the codes used for my (Marco Schöb) master thesis to create the plots for the runtime analysis of my changes to rumbleDB.

# Steps to reproduce results
## Download and prepare data
1. Download [this](https://opendata.swiss/en/dataset/zurcher-stillstandsprotokolle-des-17-jahrhunderts) zip archive and rename it to 'protocols1k' and place it in the data folder.
2. Download the files specified in 'edgards_to_download.md' and place them into a 'edgar16' directory inside data.
2. multiply data
```
cd data
chmod +x ./copy_files_protocols.sh
./copy_files_protocols.sh
cd ..

chmod +x ./copy_files_edgars.sh
./copy_files_edgars.sh
cd ..
````


## Run performance tests
5. clone rumble
```
git clone http://gitlab.inf.ethz.ch/gfourny/rumble.git
```
6. run script that runs all tests
```
chmod +x ./run_pagination.sh
chmod +x ./run_steps.sh
chmod +x ./run_steps_impact.sh
chmod +x ./run_all_tests.sh
./run_all_tests.sh
````

## Evaluate results
Run python script that plots the results (needs pandas, numpy and matplotlib installed)
```
python3 plot.py
```