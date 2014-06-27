package main.Service.Settings.Player;

import main.Interface.Log.LogWrapper;
import main.Json.JsonUtilities;
import main.Service.Settings.Directories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains all Minecraft-Accounts.
 *
 * @author Max
 */
public class Accounts
{

    private static transient Accounts   _instance   = new Accounts();
    private final            String     authServer  = "https://authserver.mojang.com";
    private transient        LogWrapper logger      = LogWrapper.getLogger( Accounts.class );
    private                  List<User> userList    = new ArrayList<User>();
    private                  String     clientToken = "";

    /**
     * The method returns the user list.
     *
     * @return a List<User> object.
     */
    public List<User> getUserList ()
    {

        return userList;
    }

    /**
     * The method returns a user by matching the given in-game name with the stored data. If an account contains the wanted string, the method will return that user instance. Else it returns null.
     *
     * @param playerName is the wanted in-game name
     * @return a User object that corresponds to the search string. Elsewhere it returns null.
     */
    public User getUserByName ( String playerName )
    {

        for ( User user : userList )
        {
            if ( user.getName().equals( playerName ) )
            {
                return user;
            }
        }
        return null;
    }

    public void load ()
    {

        logger.info( "Loading user credentials." );
        logger.debug( "Loading data from " + Directories.instance().getUser().getAbsolutePath() );

        if ( ! new File( Directories.instance().getUser() + File.separator + "user.dat" ).exists() )
        {
            logger.debug( "Loading data for first time. Creating new file." );
            try
            {
                new File( Directories.instance().getUser().getAbsolutePath() + File.separator + "user.dat" ).createNewFile();
            } catch ( IOException e )
            {
                logger.fatal( "Couldn't create a file: " + e.getMessage() );
            }
            logger.debug( "File created. Saving data." );
            save();
        } else
        {
            Accounts accounts = ( main.Service.Settings.Player.Accounts ) JsonUtilities.load( this, Directories.instance().getUser().getAbsolutePath() + File.separator + "user.dat" );
            this.userList = accounts.userList;
            this.clientToken = accounts.clientToken;
            logger.debug( "Loaded data." );
        }
    }

    public void save ()
    {

        logger.info( "Saving user data." );
        logger.debug( "Saving user data to " + Directories.instance().getUser().getAbsolutePath() );
        JsonUtilities.save( Accounts.instance(), Directories.instance().getUser().getAbsolutePath() + File.separator + "user.dat" );
    }

    public static Accounts instance ()
    {
        return _instance;
    }

    /**
     * The method creates a new user object with a name and adds it to the userList.
     *
     * @param name is the name of the user.
     * @return the freshly created user object.
     */
    public User addUser ( String name )
    {

        User user = new User( name );

        userList.add( user );

        return user;
    }

    /**
     * The method returns the proper url to the authentication server of Minecraft.
     *
     * @return an url object which contains "https://authserver.mojang.com".
     */
    public String getAuthServer ()
    {

        return authServer;
    }

    /**
     * The method returns the clientToken, which is used to identify the client at the Ygdrasil-server of Mojang. If the string is empty a random hexadecimal code will be generated.
     *
     * @return a string containing the clientToken.
     */
    public String getClientToken ()
    {

        if ( clientToken.isEmpty() )
        {
            logger.debug( "Token is empty.<br> A new one will be generated." );
            clientToken = java.util.UUID.randomUUID().toString();
            clientToken = clientToken.replaceAll( "-", "" );
            logger.debug( clientToken );
        }
        return clientToken;
    }
}
