/*
 * Created on 8 oct. 2004 for gedel_v2
 *
 * 
 */
package com.jerome.utils;

import java.util.*;

/**
 * Un genre de Map qui ressemble a une List - un genre de List qui ressemble a une Map : MapList
 * usage : 
 * <code>
 * MapList ml = new MapList();
 * ml.add(key, value);
 * ml.add(value);
 * ml.getValueAt(5);
 * ml.getValueByKey(key);
 * Iterator i_key = ml.iteratorValue();
 * Iterator i_val = ml.iteratorKey();
 * Map m = ml.getMap();
 * List l = ml.getListValue();
 * </code>
 * @author jerome forestier @ sqli
 */
public class MapList
{
    private Map  mapKeyValue; // (key, value)
    private Map  mapIdx; // (key, Integer(idx in listValue)
    
    private List listValue; // Liste d'objet
    private List listKey; // List d'objet 
    
    /**
     * Crée une MapList vide, de capacité initiale de 10
     */
    public MapList()
    {
        this(10);
    }
    
    /**
     * Crée une MapList avec une capacité initiale indiqué
     * @param initSize Capacité initiale
     */
    public MapList(int initSize)
    {
        super();
        mapKeyValue = new HashMap(initSize);
        mapIdx = new HashMap(initSize);
        listKey = new ArrayList(initSize);
        listValue = new ArrayList(initSize);
    }
    
    /**
     * Retourne la position de la clé dans la liste
     * @param key
     * @return -1 si n'existe pas
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    private int getKeyIndexInList(Object key)
    {
        Object o = mapIdx.get(key);
        if (null == o) return -1;
        return ((Integer)o).intValue();
    }
    
    /**
     * Ajoute un objey value avec une clé key. Si la clé existe déja, elle est remplacé.
     * @param key
     * @param value
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public void add(Object key, Object value)
    {
        Object o = mapKeyValue.get(key);
        if (null == o)
        {
            mapKeyValue.put(key, value);
            listKey.add(key);
            int idx = listValue.size();
            listValue.add(value);                     
            mapIdx.put(key, new Integer(idx));
        }
        else
        {
            mapKeyValue.put(key, value);
            int idx = getKeyIndexInList(key);
            listValue.set(idx, value);            
        }
    }
    
    /**
     * Ajoute un objet Value. Utilise en interne une clé = au hashcode de value
     * @param value
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public void add(Object value)
    {        
        String key =  "" + value.hashCode();
        add(key, value);
    }
    
    /**
     * Retourne un element de la liste
     * @param listIndex
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public Object getValueAt(int listIndex)
    {
        return listValue.get(listIndex);
    }
    
    /**
     * Retourne un element de la liste
     * @param key
     * @return null si n'existe pas
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public Object getValueByKey(Object key)
    {
        int idx = getKeyIndexInList(key);
        if (-1 == idx) return null;
        return getValueAt(idx);
    }

    
    /**
     * Retourne si un element o existe dans la liste des valeurs.
     * @param o element whose presence in this list is to be tested. 
     */
    boolean contains(Object o)
    {
        return mapKeyValue.get(o) != null;
    }

    /**
     * Retourne un iterator sur les valeurs de la MapList
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */        
    public Iterator iteratorValue()
    {
        return listValue.iterator();
    }
    
    /**
     * Retourne un iterator sur les clés de la MapList
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public Iterator iteratorKey()
    {
        return listKey.iterator();
    }
    
    /**
     * Retourne le liste des valeurs de la MapList
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public List getListValue()
    {
        return listValue;
    }
    
    /**
     * Retourne la map des clés/valeurs
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public Map getMap()
    {
        return mapKeyValue;
    }
    
    /**
     * Retourne la liste des clé 
     * @return
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public List getListKey()
    {
        return listKey;
    }
    
    
}
