/*
 * Created on 5 sept. 2004
 *
 * Cette interface est a utiliser par les objets qui doivent uniquement écrire 
 * dans les logs. L'interface n'a pas d'option de mise a jour des propriétés du logger
 */
package ecole.utils.logger;

/**
 * @author jemore
 *
 */
public interface LoggerUseInterface {
	void logDebug(String str);
	void logInfo(String str);
	void logWarning(String str);
	void logError(String str);
	void logFatal(String str);	
}
