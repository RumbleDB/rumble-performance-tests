cd rumble/
git checkout mschoeb-master2
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestFinalPagination

cd rumble/
git checkout mschoeb-master1
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestNewPagination

cd rumble/
git checkout old-master
git pull
mvn clean compile assembly:single
cd ..

mvn test -Dtest=TestOldPagination