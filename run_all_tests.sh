cd rumble/
git checkout mschoeb-master2
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestSaxonSteps,TestRumbleSteps,TestNewPagination || true

cd rumble/
git checkout old-master
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestOldPagination