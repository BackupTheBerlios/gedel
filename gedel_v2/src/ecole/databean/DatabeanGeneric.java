/*
 * Created on 23 ao�t 2004
 */
package ecole.databean;

/**
 * Ce databean g�n�rique est l'ancetre des databean mappant 
 * les champs de la BD avec les objets java.
 * @author Jerome
 */
public abstract class DatabeanGeneric {
    
    /**
     * Doit retourner pour le bean, la cl� unique. Null si aucune cl� n'existe.
     * En g�n�ral, c'est un ID sous forme de string.
     * @return la cl� primaire
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    abstract String getPrimaryKey();
}
