cd rumble/
git checkout mschoeb-master2
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestRumbleImprovedSteps#testMaster2

cd rumble/
git checkout mschoeb-master1
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestRumbleSteps#testMaster1