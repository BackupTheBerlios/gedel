/*
 * Created on 23 août 2004
 */
package ecole.databean;

/**
 * Ce databean générique est l'ancetre des databean mappant 
 * les champs de la BD avec les objets java.
 * @author Jerome
 */
public abstract class DatabeanGeneric {
    
    /**
     * Doit retourner pour le bean, la clé unique. Null si aucune clé n'existe.
     * En général, c'est un ID sous forme de string.
     * @return la clé primaire
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    abstract String getPrimaryKey();
}
