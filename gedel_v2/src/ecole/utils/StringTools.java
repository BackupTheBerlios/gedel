/*
 * Created on 14 sept. 2004
 *
 */
package ecole.utils;

/**
 * TODO : Add javadoc here
 * 
 * @author jemore
 */
public final class StringTools
{
	public static final boolean isEmptyStr(String str)
	{
		if (null == str || "".equals(str))
			return true;
		else
			return false;
	}
}
