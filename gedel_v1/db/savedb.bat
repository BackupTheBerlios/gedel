@echo off
@rem e':‚    a`:…  e`:Š
set MYSQL=e:\mysql\bin\
set DB=ecole
set BACKUP=ecole.bak.sql

echo Sauvegarde des donn's ECOLE.
echo MySQL est install‚ ici : %MYSQL%
echo Le backup est %BACKUP%
pause
%MYSQL%mysqldump --add-drop-table --add-locks --lock-tables %DB% > %BACKUP%
echo **** FIN **** 
pause
