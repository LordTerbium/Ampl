package main.Service.Authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * The class is a JSON-structure for the error handling during the authentication process. Created by Max on 23.06.2014.
 */
public class ErrorResponse
{

    private String             error        = "";
    private String             errorMessage = "";
    private String             cause        = "";
    private Map<Error, String> errorMap     = new HashMap<Error, String>( 6 );

    /**
     * The constructor gives access to a set of methods related to error handling during the authentication process
     */
    public ErrorResponse ()
    {

        errorMap.put( Error.METHOD_NOT_ALLOWED, "Something other than a POST request was recieved. What are you trying to do?" );
        errorMap.put( Error.NOT_FOUND, "You called a non existing endpoint. What are you working out?" );
        errorMap.put( Error.FORBIDDEN_OPERATION_EXCEPTION, "This account doesn't exist. Control your username and password." );
        errorMap.put( Error.USER_MIGRATED_EXCEPTION, "Invalid username. The account was migrated. Try with your Mojang credentials." );
        errorMap.put( Error.ILLEGAL_ARGUMENT_EXCEPTION, "Username or password were not submitted." );
        errorMap.put( Error.UNSUPPORTED_MEDIA_TYPE, "Data was not submitted as application/json." );
    }

    /**
     * The method returns a description of the caused error.
     *
     * @return a String with a short explanation of the error.
     */
    protected String getErrorReport ()
    {

        return errorMap.get( this.getErrorCode() );
    }

    /**
     * The method returns a code representing the returned exception.
     *
     * @return an Enum-constant of Error.
     */
    protected Error getErrorCode ()
    {

        if ( error.equals( "Method Not Allowed" ) )
        {
            return Error.METHOD_NOT_ALLOWED;
        } else if ( error.equals( "Not Found" ) )
        {
            return Error.NOT_FOUND;
        } else if ( error.equals( "ForbiddenOperationException" ) )
        {
            if ( cause.equals( "UserMigratedException" ) )
            {
                return Error.USER_MIGRATED_EXCEPTION;
            } else
            {
                return Error.FORBIDDEN_OPERATION_EXCEPTION;
            }
        } else if ( error.equals( "IllegalArgumentException" ) )
        {
            return Error.ILLEGAL_ARGUMENT_EXCEPTION;
        } else if ( error.equals( "Unsupported Media Type" ) )
        {
            return Error.UNSUPPORTED_MEDIA_TYPE;
        } else
        {
            return Error.UNKNOWN_EXCEPTION;
        }
    }

    /**
     * The method returns the original error description given by the server.
     *
     * @return is a String with a description of the error.
     */
    protected String getErrorMessage ()
    {

        return this.errorMessage;
    }

    /**
     * An Enum with all known and unknown exceptions. This simplifies the error handling reducing the amount of lines to write.
     */
    protected enum Error
    {
        METHOD_NOT_ALLOWED( 1 ), NOT_FOUND( 2 ), FORBIDDEN_OPERATION_EXCEPTION( 3 ), USER_MIGRATED_EXCEPTION( 4 ),
        ILLEGAL_ARGUMENT_EXCEPTION( 5 ), UNSUPPORTED_MEDIA_TYPE( 6 ), UNKNOWN_EXCEPTION( 7 );

        private int i;

        Error ( int i )
        {

            this.i = i;
        }
    }
}
