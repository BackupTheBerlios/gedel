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
 * List l = ml.getList();
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
     * 
     */
    public MapList()
    {
        super();        
        mapKeyValue = new HashMap();
        mapIdx = new HashMap();
        listKey = new ArrayList();
        listValue = new ArrayList();
    }
    
    /**
     * 
     * @param initSize
     */
    public MapList(int initSize)
    {
        super();
        mapKeyValue = new HashMap(initSize);
        mapIdx = new HashMap(initSize);
        listKey = new ArrayList(initSize);
        listValue = new ArrayList(initSize);
    }
    
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
    
    public Iterator iteratorValue()
    {
        return listValue.iterator();
    }
    
    public Iterator iteratorKey()
    {
        return listKey.iterator();
    }
    
    public List getListValue()
    {
        return listValue;
    }
    
    
}
