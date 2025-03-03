cd rumble/
git checkout mschoeb-master2
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestSaxonSteps,TestRumbleImprovedSteps
# mvn test -Dtest=TestSaxonSteps,TestRumbleImprovedSteps,TestNewPagination

cd rumble/
git checkout master
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestRumbleSteps

cd rumble/
git checkout old-master
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestOldPagination