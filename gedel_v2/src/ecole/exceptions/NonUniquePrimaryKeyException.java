/*
 * Created on 1 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.exceptions;

/**
 * Exception en cas de violation de cl�e primaire unique.
 * Cette exception est lanc� si une cl�e n'est pas primaire, alors qu'elle devrait l'�tre.      
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
