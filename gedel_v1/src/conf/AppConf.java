/*
 * Created on 28 août 2004
 *
 */
package conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Jerome
 * 
 * Classe permettant de gérer la lecture et l'écriture dans un fichier de conf
 * nommé "ecole.ini" (propertiesFile)<br>
 * exemple :<br>
 * <code>
 * AppConf.getInstance().getProperty("prop", "defaultValue");
 * AppConf.getInstance().setProperty("prop", "realValue");
 * AppConf.getInstance().store();
 * </code>
 * 
 * @pattern singleton
 */
public class AppConf
{

    private Logger log;
    // Instance pour le singleton
    private static AppConf instance = new AppConf();

    /**
     * Constructeur privé pour le singleton
     */
    private AppConf()
    {
        super();
        readFromFile();
    }

    /**
     * Retourne l'instance du singleton
     * 
     * @return AppConf
     */
    public static AppConf getInstance()
    {
        return instance;
    }

    // Nom du fichier de conf
    private String propertiesFile = "ecole.ini";

    // Objet de propriété
    Properties properties = new Properties();

    /**
     * Ecriture de log
     * 
     * @param str
     */
    private void log(String str)
    {
        System.out.println(str);
    }

    /**
     * Chargement du fichier de conf
     *  
     */
    private void readFromFile()
    {
        try
        {
            File f = new File(propertiesFile);
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
    
    public void store()
    {
        File f = new File(propertiesFile);
        try
        {
            FileOutputStream fos = new FileOutputStream(f);
            properties.store(fos,
                    "Projet : ecole");
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

    /**
     * Retourne une propriété
     * 
     * @param propertyName
     * @return
     */
    public String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName);
    }

    /**
     * Retourne une propriété. Si la propriété n'existe pas, la defaultValue est
     * retournée
     * 
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public String getProperty(String propertyName, String defaultValue)
    {
        return properties.getProperty(propertyName, defaultValue);
    }

    /**
     * Retourne une propriété. Si la propriété n'existe pas, la defaultValue est
     * retournée
     * 
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public int getPropertyAsInt(String propertyName, int defaultValue)
    {
        String value = properties.getProperty(propertyName, "" + defaultValue);
        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Retourne une propriété. Si la propriété n'existe pas, la defaultValue est
     * retournée
     * 
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public double getPropertyAsDouble(String propertyName, double defaultValue)
    {
        String value = properties.getProperty(propertyName, "" + defaultValue);
        try
        {
            return Double.parseDouble(value);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }
    
    /**
     * Affectue une propriété. N'est pas inscrit dans le fichier
     * tant que store() n'est pas appelé.
     * @param propertyName
     * @param value
     * @see store
     */
    public void setProperty(String propertyName, String value)
    {
        properties.setProperty(propertyName, value);
    }

    /**
     * Affectue une propriété. N'est pas inscrit dans le fichier
     * tant que store() n'est pas appelé.
     * @param propertyName
     * @param value
     */
    public void setProperty(String propertyName, int value)
    {
        properties.setProperty(propertyName, Integer.toString(value));
    }
    
    /**
     * Affectue une propriété. N'est pas inscrit dans le fichier
     * tant que store() n'est pas appelé.
     * @param propertyName
     * @param value
     */
    public void setProperty(String propertyName, double value)
    {
        properties.setProperty(propertyName, Double.toString(value));
    }
}