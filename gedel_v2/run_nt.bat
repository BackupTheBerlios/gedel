@echo off

echo Demarrage de la base de donnée...
net start mysql > nul

echo Demarrage de java ...
java -cp bin/;lib/mysql-connector-java-3.0.14-production-bin.jar;lib/smartjcommon_1.4.jar;lib/smartjprint_1.4.1.jar ecole.gui.EcoleApp

echo Arret de la base de donnée...
net stop mysql
pause

