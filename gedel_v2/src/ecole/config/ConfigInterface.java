/*
 * Created on 14 sept. 2004
 *
 * 
 */
package ecole.config;

import ecole.utils.logger.LoggerUseInterface;

/**
 * @author jemore
 */
public interface ConfigInterface
{    
    void loadConfigFile(String configFile);
    void saveConfigFile();
    String getString(String entry, String defaultValue);
    void setString(String entry, String value);
    void setLogger(LoggerUseInterface logger);
}
