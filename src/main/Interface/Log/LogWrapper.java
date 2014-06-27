package main.Interface.Log;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.*;

/**
 * The class provides a smart solution for a often changing problem: how to log information. The class wraps all the needed functions for the whole programm and passes the input to the corresponding framework, at the time of writing the java.util.Logger . By adding this log-wrapper not only the entire code uses the same 6 functions but it also
 * automatically adds two handlers: a custom console handler and a file handler.
 *
 * @author Max
 */
public class LogWrapper
{

    private Logger logger = null;

    /**
     * This constructor is used by the getLogger method to add a new logger.
     *
     * @param c is the class object of the created logger.
     */
    private LogWrapper ( Class<?> c )
    {

        this.logger = Logger.getLogger( c.getName() );
    }

    /**
     * The method should be called when the application starts, so that all following loggers will take this settings.
     */
    public static void setupLogger ()
    {

        Logger LOGGER = Logger.getLogger( "" );
        FileHandler fileTxt = null;
        try
        {
            fileTxt = new FileHandler( "MyLogger%g.log", 10000000, 3, false );
        } catch ( SecurityException e )
        {
            JOptionPane.showMessageDialog( null, "Error", "Security Exception: " + e.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE );
        } catch ( IOException e )
        {
            JOptionPane.showMessageDialog( null, "Error", "I am missing read and write permissions: " + e.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE );
        }
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter( formatterTxt );
        HtmlFormatter formatterHtml = new HtmlFormatter();
        ConsoleHandler consoleHtml = new ConsoleHandler();
        consoleHtml.setLevel( Level.FINE );
        consoleHtml.setFormatter( formatterHtml );
        LOGGER.setUseParentHandlers( true );
        LOGGER.addHandler( fileTxt );
        LOGGER.addHandler( consoleHtml );
        LOGGER.setLevel( Level.INFO );
        Logger.getLogger( "main" ).setLevel( Level.ALL );
    }

    /**
     * The method tries to get the name of the class where the log comes from. If it already exist it will use the old one, saving memory and calculation power. New logger will be added to that list.
     *
     * @param c is the class object of the created logger.
     * @return the LogWrapper object with an attached java.util.logging logger.
     */
    public static LogWrapper getLogger ( Class<?> c )
    {

        LogWrapper logWrapper = new LogWrapper( c );
        return logWrapper;
    }

    /**
     * The method simply returns true if fatal logging level is enabled. It is part of a saveGuard.
     *
     * @return a boolean telling the ability to log the required level.
     */
    public boolean isFatalLoggable ()
    {

        return logger.isLoggable( Level.SEVERE );
    }

    /**
     * The method simply returns true if error logging level is enabled. It is part of a saveGuard.
     *
     * @return a boolean telling the ability to log the required level.
     */
    public boolean isErrorLoggable ()
    {

        return logger.isLoggable( Level.WARNING );
    }

    /**
     * The method simply returns true if config logging level is enabled. It is part of a saveGuard.
     *
     * @return a boolean telling the ability to log the required level.
     */
    public boolean isConfigLoggable ()
    {

        return logger.isLoggable( Level.CONFIG );
    }

    /**
     * The method simply returns true if info logging level is enabled. It is part of a saveGuard.
     *
     * @return a boolean telling the ability to log the required level.
     */
    public boolean isInfoLoggable ()
    {

        return logger.isLoggable( Level.INFO );
    }

    /**
     * The method simply returns true if debug logging level is enabled. It is part of a saveGuard.
     *
     * @return a boolean telling the ability to log the required level.
     */
    public boolean isDebugLoggable ()
    {

        return logger.isLoggable( Level.FINE );
    }

    /**
     * The method parses a log with the level fatal, equivalent to severe, to the underlying logging framework.
     *
     * @param message is a String containing the text to log.
     */
    public void fatal ( String message )
    {

        logger.severe( message );
    }

    /**
     * The method parses a log with the level fatal, equivalent to severe, to the underlying logging framework. By using this method you can also prompt the stack-trace.
     *
     * @param message is a String containing the text to log.
     */
    public void fatal ( String message, Throwable t )
    {

        logger.log( Level.SEVERE, message, t );
    }

    /**
     * The method parses a log with the level error, equivalent to warning, to the underlying logging framework.
     *
     * @param message is a String containing the text to log.
     */
    public void error ( String message )
    {

        logger.warning( message );
    }

    /**
     * The method parses a log with the level error, equivalent to warning, to the underlying logging framework. By using this method you can also prompt the stack-trace.
     *
     * @param message is a String containing the text to log.
     */
    public void error ( String message, Throwable t )
    {

        logger.log( Level.WARNING, message, t );
    }

    /**
     * The method parses a log with the level config, equivalent to config, to the underlying logging framework.
     *
     * @param message is a String containing the text to log.
     */
    public void config ( String message )
    {

        logger.config( message );
    }

    /**
     * The method parses a log with the level info, equivalent to info, to the underlying logging framework.
     *
     * @param message is a String containing the text to log.
     */
    public void info ( String message )
    {

        logger.info( message );
    }

    /**
     * The method parses a log with the level debug, equivalent to fine, to the underlying logging framework.
     *
     * @param message is a String containing the text to log.
     */
    public void debug ( String message )
    {

        logger.fine( message );
    }

    /**
     * The method returns the actual global logging level of the application.
     *
     * @return a level of the Level class, such as INFO.
     */
    public Level getLogLevel ()
    {

        return LogManager.getLogManager().getLogger( Logger.GLOBAL_LOGGER_NAME ).getLevel();
    }

    /**
     * The method is used to change the global logging level of the application.
     *
     * @param level is a level of the Level class, such as FINE.
     */
    public void setLogLevel ( Level level )
    {

        LogManager.getLogManager().getLogger( Logger.GLOBAL_LOGGER_NAME ).setLevel( level );
    }
}
