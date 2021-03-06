@Echo	Compiere Full Database Export 	$Revision: 1.2 $

@Rem $Id: DBExportFull.bat,v 1.2 2005/01/22 21:59:15 jjanke Exp $

@Echo Saving database %1@%COMPIERE_DB_NAME% to %COMPIERE_HOME%\data\ExpDatFull.dmp

@if (%COMPIERE_HOME%) == () goto environment
@if (%COMPIERE_DB_NAME%) == () goto environment
@Rem Must have parameter: systemAccount
@if (%1) == () goto usage

@exp %1@%COMPIERE_DB_NAME% FILE=%COMPIERE_HOME%\data\ExpDatFull.dmp Log=%COMPIERE_HOME%\data\ExpDat.log CONSISTENT=Y FULL=Y

@cd %COMPIERE_HOME%\data
@jar cvfM data\ExpDatFull.jar ExpDatFull.dmp

@goto end

:environment
@Echo Please make sure that the enviroment variables are set correctly:
@Echo		COMPIERE_HOME	e.g. D:\Compiere2
@Echo		COMPIERE_DB_NAME	e.g. dev1.compiere.org

:usage
@echo Usage:		%0 <systemAccount>
@echo Examples:	%0 system/manager

:end
