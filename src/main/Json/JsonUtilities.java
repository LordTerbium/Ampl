package main.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import main.Interface.Log.LogWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class provides a de-serializing and a serializing method for all json-files used in the application.
 *
 * @author Max
 */
public class JsonUtilities {

    private final static LogWrapper logger = LogWrapper.getLogger( JsonUtilities.class );

    private JsonUtilities () {
        // Nothing in here
    }

    /**
     * The method saves any provided json-structured class to the given file. It serializes the content in a
     * human-readable way and also serializes nulls.
     *
     * @param jsonModel is the object with the json structure inside.
     * @param filePath  is the location where the file will be saved.
     */
    public static void save ( Object jsonModel, String filePath ) {

        logger.debug( "Creating GsonBuilder." );

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        logger.debug( "Parsing data..." );

        String content = gson.toJson( jsonModel );

        logger.debug( "Finished parsing data.<br> Writing  now to the file." );

        try {
            FileWriter writer = new FileWriter( filePath );
            logger.debug( "Writing to " + filePath );
            writer.write( content );
            writer.close();
            logger.debug( "Saved changes to the file." );
        } catch ( IOException e ) {
            logger.fatal(
                    "You are executing the launcher from a location without write permission.</br> Change this to " +
                            "save any options.<br>" +
                            e.getMessage() );
        }
    }

    public static Object load ( Object jsonModel, String filePath ) {

        logger.debug( "Initialize de-serializer ..." );
        Gson gson = new Gson();

        try {

            logger.debug( "Reading data from file ..." );
            BufferedReader br = new BufferedReader( new FileReader( filePath ) );


            logger.debug( "De-serializing data ..." );
            jsonModel = gson.fromJson( br, jsonModel.getClass() );

        } catch ( IOException e ) {

            logger.error( "Couldn't find the file. Try to restart the application.<br>" + e.getMessage() );

        } catch ( JsonSyntaxException e ) {

            logger.error( "Couldn't read the file. Did you change something?<br>" + e.getMessage() );

        }
        logger.debug( "Finished de-serializing." );

        return jsonModel;
    }
}
