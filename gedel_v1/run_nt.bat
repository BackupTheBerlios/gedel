@echo off

echo Demarrage de la base de donnée...
net start mysql

echo Demarrage de l'application...
java -cp bin/;META-INF/lib/mysql-connector-java-3.0.14-production-bin.jar;META-INF/lib/smartjcommon_1.4.jar;META-INF/lib/smartjprint_1.4.1.jar ecole/EcoleApp

echo Arret de la base de donnée...
net stop mysql
pause

