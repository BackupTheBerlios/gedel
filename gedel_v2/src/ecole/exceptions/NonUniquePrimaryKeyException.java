/*
 * Created on 1 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.exceptions;

/**
 * Exception en cas de violation de clée primaire unique.
 * Cette exception est lancé si une clée n'est pas primaire, alors qu'elle devrait l'être.      
 * @author jerome forestier @ sqli
 */
public class NonUniquePrimaryKeyException extends Exception
{

    public NonUniquePrimaryKeyException()
    {
        super();
    }

    public NonUniquePrimaryKeyException(String message)
    {
        
        super(message);
    }
    
    public NonUniquePrimaryKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonUniquePrimaryKeyException(Throwable cause) {
        super(cause);
    }    

}
