/*
 * Created on 28 août 2004
 *
 */
package ecole.utils.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jerome
 * 
 * Classe de log.
 * 
 * @pattern singleton
 */
public class Logger implements LoggerUseInterface
{
    private static Logger instance = new Logger();

    private Logger()
    {

    }

    public static Logger getInstance()
    {
        return instance;
    }

    private int level = LEVEL_DEBUG;

    public static int LEVEL_DEBUG = 0;

    public static int LEVEL_INFO = 1;

    public static int LEVEL_WARNING = 2;

    public static int LEVEL_ERROR = 3;

    public static int LEVEL_FATAL = 4;

    private static String[] levelStr = { "DEBUG", "INFO ", "WARN ", "ERROR",
            "FATAL" };

    private DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss.SSS");

    private void out(String str, int level)
    {
        if (level >= LEVEL_ERROR)
        {
            System.err.print(dateFormat.format(new Date()));
            System.err.print('>');
            System.err.print(levelStr[level]);
            System.err.print(' ');
            System.err.println(str);
        }
        else
        {
            System.out.print(dateFormat.format(new Date()));
            System.out.print('>');
            System.out.print(levelStr[level]);
            System.out.print(' ');
            System.out.println(str);
        }
    }

    public void log(String str, int level)
    {
        if (level <= this.level || level == LEVEL_INFO)
        {
            out(str, level);
        }
    }

    public boolean isDebugEnabled()
    {
        return level <= LEVEL_DEBUG;
    }

    public boolean isInfoEnabled()
    {
        return level <= LEVEL_INFO;
    }

    public boolean isWarningEnabled()
    {
        return level <= LEVEL_WARNING;
    }

    public boolean isErrorEnabled()
    {
        return level <= LEVEL_ERROR;
    }

    public boolean isFatalEnabled()
    {
        return level <= LEVEL_FATAL;
    }

    public void logDebug(String str)
    {
        log(str, LEVEL_DEBUG);
    }

    public void logInfo(String str)
    {
        log(str, LEVEL_INFO);
    }

    public void logWarning(String str)
    {
        log(str, LEVEL_WARNING);
    }

    public void logError(String str)
    {
        log(str, LEVEL_ERROR);
    }

    public void logFatal(String str)
    {
        log(str, LEVEL_FATAL);
    }

    /**
     * @return Returns the level.
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level
     *            The level to set.
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
}