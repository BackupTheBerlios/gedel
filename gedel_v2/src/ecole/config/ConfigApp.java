/*
 * Created on 14 sept. 2004
 *
 * 
 */
package ecole.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import ecole.utils.logger.LoggerUseInterface;

/**
 * @author jemore
 */
public final class ConfigApp implements ConfigInterface
{

    private Properties properties = new Properties();
    private LoggerUseInterface logger;
    private String propertiesFile;

    public ConfigApp(String configFile)
    {
        loadConfigFile(configFile);
    }

    private void log(String str)
    {
        if (null != logger)
        {
            logger.logDebug(str);
        }
        else
        {
            System.out.println(str);
        }
    }

    /* (non-Javadoc)
     * @see ecole.config.ConfigInterface#getConfigFile(java.lang.String)
     */
    public void loadConfigFile(String configFile)
    {
        try
        {
            this.propertiesFile = configFile;
            File f = new File(configFile);
            log("Fichier de propriétés : " + f.getAbsolutePath());
            if (!f.canRead())
            {
                log("Le fichier n'existe pas");
                properties.clear();
                return;
            }
            FileInputStream fis = new FileInputStream(f);
            properties.load(fis);
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see ecole.config.ConfigInterface#setLogger(ecole.utils.logger.LoggerUseInterface)
     */
    public void setLogger(LoggerUseInterface logger)
    {
        this.logger = logger;
    }

    /* (non-Javadoc)
     * @see ecole.config.ConfigInterface#saveConfigFile()
     */
    public void saveConfigFile()
    {
        File f = new File(propertiesFile);
        try
        {
            log("Enregistrement des propriétés de " + propertiesFile);
            FileOutputStream fos = new FileOutputStream(f);
            properties.store(fos, "Classe : " + this.getClass().getName());
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see ecole.config.ConfigInterface#getString(java.lang.String, java.lang.String)
     */
    public String getString(String entry, String defaultValue)
    {
        return properties.getProperty(entry, defaultValue);
    }

    /* (non-Javadoc)
     * @see ecole.config.ConfigInterface#setString(java.lang.String, java.lang.String)
     */
    public void setString(String entry, String value)
    {
        properties.setProperty(entry, value);
    }

}
