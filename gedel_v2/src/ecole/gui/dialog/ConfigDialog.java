/*
 * Created on 15 sept. 2004
 *
 */
package ecole.gui.dialog;

import java.awt.Window;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.plaf.Options;

import ecole.config.ConfigApp;
import ecole.config.ConfigInterface;
import ecole.gui.predefinedframe.DialogAlert;
import ecole.gui.predefinedframe.DialogInput;

/**
 * Gestion des fenetres de dialogue permettant la configuration de l'application
 * 
 * @author jemore
 */
public final class ConfigDialog
{
    Window parentWindow = null;

    /**
     * Constructeur sans parametres, les boites de dialogues sont détachés de la
     * fenetre appelante
     *
     */
    public ConfigDialog()
    {
        loadConfig();
    }

    private static final String INTERNAL_PROPERTIES_FILE = "ConfigDialog.properties";
    private Properties config = new Properties();

    /**
     * Constructeur avec en parametre la fenetre qui demande les boites
     * de dialogues
     * @param parentWindow fenetre appelante
     */
    public ConfigDialog(Window parentWindow)
    {
        this.parentWindow = parentWindow;
        loadConfig();
    }

    private final void loadConfig()
    {
        try
        {
            InputStream f = this.getClass().getResource(INTERNAL_PROPERTIES_FILE).openStream();
            config.load(f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Demande la configuration de la base de données.
     * Si l'utilisateur clique sur OK, la config est enregistré.
     * @return true si la config a changé, false sinon.
     * @author jemore
     */
    public final boolean askUserDBConfig()
    {
        ConfigInterface configDb = new ConfigApp("app.conf");
        List result =
            DialogInput.AskUser(
                parentWindow,
                "Paramètres de la base",
                "Saisissez les paramètres de connexion à la base de donnée",
                new String[] { "Nom de host", "Nom de la base", "Nom de l'utilisateur", "Mot de passe" },
                new String[] { configDb.getString("db.host", "localhost"), configDb.getString("db.name", "ecole"), configDb.getString("db.user", "root"), configDb.getString("db.pass", "")});
        if (null != result)
        {
            if (DialogAlert.DialogWarningYesNo(parentWindow, "Etes-vous sur de vouloir changer " + "les paramètres de la base de données ?\n C'est très dangereux..."))
            {
                configDb.setString("db.host", (String) result.get(0));
                configDb.setString("db.name", (String) result.get(1));
                configDb.setString("db.user", (String) result.get(2));
                configDb.setString("db.pass", (String) result.get(3));
                configDb.saveConfigFile();
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public final void askUserLooknfeel()
    {
        // LnF disponible, dans le fichier .properties de la classe  
        String lnf_name_string = (String) config.get("lnf_name");
        String[] lnf_names = lnf_name_string.split(";");

        ConfigInterface configLNF = new ConfigApp("app.conf");
        int old_looknfeel = Integer.parseInt(configLNF.getString("look_n_feel", "0")); 
        // Préparation de la boite de dialogue
        JComboBox combo = new JComboBox(lnf_names);
        combo.setSelectedIndex(old_looknfeel);

        int idx_selected = DialogInput.AskUser(parentWindow, "Configuration du LnF", "Selectionnez l'habillage des fenêtres par défaut", combo, 0);
        if (idx_selected != -1)
        {
            try
            {
                changeLookNfeel(idx_selected);
                // Enregistrement dans la conf du LnF choisi
                
                configLNF.setString("look_n_feel", "" + idx_selected);
                configLNF.saveConfigFile();
                configLNF = null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                DialogAlert.DialogError(parentWindow, "Configuration du LnF", "Impossible de changer l'habillage des fenêtres");
            }
        }
    }
    
    public final void setUserLooknfeelFromConfig()
    {
        try
        {
            ConfigInterface configLNF = new ConfigApp("app.conf");
            String str_idx_selected = configLNF.getString("look_n_feel", "0");
            int idx = Integer.parseInt(str_idx_selected); 
            changeLookNfeel(idx);
        } catch (Exception e)
        {
            System.err.println("Impossible de forcer le Look n feel");
            e.printStackTrace();
        }
    }

    /**
     * @param idx_selected
     * @author jemore
     */
    private final void changeLookNfeel(int idx_selected) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        if (idx_selected == -1 ) return;
        if (0 == idx_selected)
        {
            // Le plus adapté
            UIManager.setLookAndFeel(Options.getSystemLookAndFeelClassName());

        }
        else if (1 == idx_selected)
        {
            // JLooks
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        else if (2 == idx_selected)
        {
            // JLooks
            UIManager.setLookAndFeel(Options.getCrossPlatformLookAndFeelClassName());
        }
        SwingUtilities.updateComponentTreeUI(parentWindow);
        //parentWindow.pack();
    }


}