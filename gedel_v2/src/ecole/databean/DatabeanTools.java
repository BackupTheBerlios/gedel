/*
 * Created on 1 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.databean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ecole.exceptions.NonUniquePrimaryKeyException;

/**
 * DatabeanTools
 * @author jerome forestier @ sqli
 */
public final class DatabeanTools
{
    /**
     * <p>
     * Transforme une liste de Databean (ou d'h�ritiers de databean) en une map,
     * en utilisant la cl� primaire getPrimaryKey de chaque bean comme cl� de la map cr�e.
     * </p>
     * Si une cl� primaire est en double dans la map, une exception est lev�.<br>
     * Si la cl� primaire est null, l'objet n'est pas ajout� dans la map.
     * @param list de GenericDatabean (ou d'h�ritier)
     * @return Map contenant les databean
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public final static Map createMapFromList(List listDatabean) throws NonUniquePrimaryKeyException
    {
        Map map = new HashMap(listDatabean.size());
        Iterator i = listDatabean.iterator();
        GenericDatabean bean;
        while (i.hasNext())
        {
            bean = (GenericDatabean)i.next();
            String key = bean.getPrimaryKey();
            if (key != null)
            {
                if (map.get(key) == null)
                {
                    map.put(key, bean);
                }
                else
                {
                    throw new NonUniquePrimaryKeyException("Le bean "+bean+" a une cl�e primaire non unique (cl�e :"+key+")");
                }
            }
        }
        return map;
    }
}
