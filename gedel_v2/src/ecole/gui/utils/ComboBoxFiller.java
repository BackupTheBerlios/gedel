/*
 * Created on 4 sept. 2004
 *
 */
package ecole.gui.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;

/**
 * Classe utilitaire pour remplir des JCombobox
 * @author jemore
 */
public final class ComboBoxFiller
{

	/**
	 *
	 * Rempli une combobox avec le contenu d'une liste, 
	 * sur laquelle on applique une methode static particuliere, retournant un string, et qui
	 * doit etre contenu dans la classe displayClass, et nommée methodToInvoke.
	 * Si la List Items contient des objets de différents type, autant de méthode
	 * d'affichage sont appelées.
	 * @param cb composant combobox
	 * @param items la List d'objet que l'on veut mettre dans la combo
	 * @param displayClass Classe d'affichage
	 * @param methodToInvoke Methode statique a appeler, pour chaque objet contenu dans la liste Items 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException la methode a appliquer n'est pas défini pour le type de l'objet de la liste
	 * @author jemore
	 */
	public final static void addItems(
		JComboBox cb,
		List items,
		Class displayClass,
		String methodToInvoke
		) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
	{
		cb.setVisible(false);
		cb.removeAllItems();
		String s = "";		
		for (Iterator i = items.iterator(); i.hasNext();)
		{			
			Object o = (Object) i.next();	
			// Get the method visitFoo(Foo foo)
			Method m = displayClass.getMethod(
				methodToInvoke,
				new Class[] { o.getClass() });
			// Try to invoke visitFoo(Foo foo)
			s = (String) m.invoke(
				displayClass, 
				new Object[] { o });
			cb.addItem(s);
		}
		cb.setVisible(true);
	}

	/**
	 * Rempli une combobox avec le contenu d'une liste,
	 * pour chaque objet de la list items, on applique une methode methodToString,
	 * retournant un string, sur l'objet contenu dans la liste.
	 * Si la methodToString n'existe pas pour l'objet, on applique la méthode toString() 
	 * @param cb
	 * @param items List d'objet, devant implémenter une methode dont le nom est spécifiée dans "methodToString"
	 * @param methodToString methode a appelé pour chaque éléments de la liste
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author jemore
	 */
	public final static void addItems(
		JComboBox cb,
		List items,
		String methodToString) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		cb.setVisible(false);
		cb.removeAllItems();
		String s = "";
		for (Iterator i = items.iterator(); i.hasNext();)
		{			
			Object o = (Object) i.next();
			String methodName = o.getClass().getName();
			methodName = methodToString;		
			try
			{
				// Get the method visitFoo(Foo foo)
				Method m = o.getClass().getMethod(
						methodName,
						new Class[] { });
				// Try to invoke visitFoo(Foo foo)
				s = (String) m.invoke(o, new Object[] { });
			} 
			catch (NoSuchMethodException e)
			{
				s = o.toString();
			}
			cb.addItem(s);
		}
		cb.setVisible(true);
	}

	/**
	 * Ajoute des items a la combobox . Les anciens items sont supprimés
	 * @param cb Composant de combobox
	 * @param items tableau de string a ajouter
	 * @author jemore
	 */
	public final static void addItems(JComboBox cb, String[] items)
	{
		cb.setVisible(false);
		cb.removeAllItems();
		int len = items.length;
		for (int i = 0; i < len; i++)
		{
			cb.addItem((String) items[i]);
		}
		cb.setVisible(true);
	}

	/**
	 * Ajoute des items a la combobox . Les anciens items sont supprimés
	 * @param cb Composant de combobox
	 * @param items tableau d'objet a ajouter, sur lequels on applique <code>toString()</code>
	 * @author jemore
	 */
	public final static void addItems(JComboBox cb, Object[] items)
	{
		cb.setVisible(false);
		cb.removeAllItems();
		int len = items.length;
		for (int i = 0; i < len; i++)
		{
			cb.addItem(items[i].toString());
		}
		cb.setVisible(true);
	}
    
/**
     *
     * Ajoute a une combobox avec le contenu d'une liste, 
     * sur laquelle on applique une methode static particuliere, retournant un string, et qui
     * doit etre contenu dans la classe displayClass, et nommée methodToInvoke.
     * Si la List Items contient des objets de différents type, autant de méthode
     * d'affichage sont appelées.
     * @param cb composant combobox
     * @param items la List d'objet que l'on veut mettre dans la combo
     * @param displayClass Classe d'affichage
     * @param methodToInvoke Methode statique a appeler, pour chaque objet contenu dans la liste Items 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException la methode a appliquer n'est pas défini pour le type de l'objet de la liste
     * @author jemore
     */
    public final static void appendItems(
        JComboBox cb,
        List items,
        Class displayClass,
        String methodToInvoke
        ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
    {
        cb.setVisible(false);        
        String s = "";      
        for (Iterator i = items.iterator(); i.hasNext();)
        {           
            Object o = (Object) i.next();   
            // Get the method visitFoo(Foo foo)
            Method m = displayClass.getMethod(
                methodToInvoke,
                new Class[] { o.getClass() });
            // Try to invoke visitFoo(Foo foo)
            s = (String) m.invoke(
                displayClass, 
                new Object[] { o });
            cb.addItem(s);
        }
        cb.setVisible(true);
    }    
}
