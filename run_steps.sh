cd rumble/
git checkout mschoeb-master2
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestRumbleImprovedSteps

cd rumble/
git checkout master
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestRumbleSteps