@echo off
@rem e':‚    a`:…  e`:Š
set MYSQL=e:\mysql\bin\
set DB=ecole

echo Creation de la base de donn‚es ECOLE.
echo Attention, si vous continuez toutes les donn‚es seront perdues.
echo MySQL est install‚ ici : %MYSQL%
pause
%MYSQL%mysqladmin drop %DB% 2>nul
%MYSQL%mysqladmin create %DB% 2>nul
echo **** Restauration des donn‚es ****
%MYSQL%mysql -e "source ecole.sql" %DB%
echo **** FIN **** 
pause
