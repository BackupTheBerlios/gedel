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
 * Classe utilitaire de manipulation des objets hérités de DatabeanGeneric
 * @author jerome forestier @ sqli
 */
public final class DatabeanTools
{
    /**
     * <p>
     * Transforme une liste de Databean (ou d'héritiers de databean) en une map,
     * en utilisant la clé primaire getPrimaryKey de chaque bean comme clé de la map crée.
     * </p>
     * Si une clé primaire est en double dans la map, une exception est levé.<br>
     * Si la clé primaire est null, l'objet n'est pas ajouté dans la map.
     * @param list de DatabeanGeneric (ou d'héritier)
     * @return Map contenant les databean
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public final static Map createMapFromList(List listDatabean) throws NonUniquePrimaryKeyException
    {
        Map map = new HashMap(listDatabean.size());
        Iterator i = listDatabean.iterator();
        DatabeanGeneric bean;
        while (i.hasNext())
        {
            bean = (DatabeanGeneric)i.next();
            String key = bean.getPrimaryKey();
            if (key != null)
            {
                if (map.get(key) == null)
                {
                    map.put(key, bean);
                }
                else
                {
                    throw new NonUniquePrimaryKeyException("Le bean "+bean+" a une clée primaire non unique (clée :"+key+")");
                }
            }
        }
        return map;
    }
}
