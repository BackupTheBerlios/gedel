/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.databean;

/**
 * BilanCantineDatabean
 * @author jerome forestier @ sqli
 */
public class BilanCantineDatabean extends DatabeanGeneric
{

    EleveDatabean eleve;
    ClasseDatabean classe;
    TarifCantineDatabean tarif;
    /* (non-Javadoc)
     * @see ecole.databean.DatabeanGeneric#getPrimaryKey()
     */
    String getPrimaryKey()
    {       
        return null;
    }

}
