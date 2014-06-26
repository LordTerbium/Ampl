package main.Service.Authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.Ampl;
import main.Interface.Log.LogWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * The class handles the authentication login process to the Yggdrasil server. It contains the login request and
 * response. Created by Max on 22.06.2014.
 */
public class LogIn {

    private LogWrapper logger = LogWrapper.getLogger(LogIn.class);

    private Request request = new Request();
    private Response response = null;
    private ErrorResponse errorResponse = null;
    private URL authServer;

    protected LogIn(String username, char[] password) {

        request.password = new String(password);
        request.username = username;
        try {
            authServer = new URL(Ampl.getSettings().getAccounts().getAuthServer() + "/authenticate");
        } catch (MalformedURLException e) {
            logger.fatal("My fault. I have a typo in the basic url. Forward this to the developer. <br>" +
                    e.getLocalizedMessage());
        }
    }

    public int login() {

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .create();
        int code = 404;
        try {
            String content = gson.toJson(this.request);
            byte[] contentBytes = content.getBytes(Charset.forName("UTF-8"));

            URLConnection connection;

            connection = authServer.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Lenght", Integer.toString(contentBytes.length));

            OutputStream requestStream = connection.getOutputStream();

            requestStream.write(contentBytes, 0, contentBytes.length);
            requestStream.close();

            BufferedReader responseStream;

            if (((HttpURLConnection) connection).getResponseCode() == 200) {
                responseStream = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                response = gson.fromJson(responseStream.readLine(), Response.class);
                code = ((HttpURLConnection) connection).getResponseCode();
            } else {
                responseStream = new BufferedReader(
                        new InputStreamReader(((HttpURLConnection) connection).getErrorStream(), "UTF-8"));
                errorResponse = gson.fromJson(responseStream.readLine(), ErrorResponse.class);
                code = ((HttpURLConnection) connection).getResponseCode();
            }

        } catch (IOException e) {
            logger.fatal("Something went wrong. Maybe its your connection status:<br>" + e.getLocalizedMessage());
        }

        return code;
    }

    /**
     * The method returns the response of the authentication server.
     *
     * @return a Response object.
     */
    public Response getResponse() {

        return response;
    }

    /**
     * The method returns the error response of the authentication server.
     *
     * @return a ErrorResponse object.
     */
    public ErrorResponse getErrorResponse() {

        return errorResponse;
    }

    /**
     * The class is the JSON structure for the authentication request of the Yggdrasil protocol.
     */
    class Request {

        @SuppressWarnings("unused")
        private Agent agent = new Agent();
        @SuppressWarnings("unused")
        private String username = "";
        @SuppressWarnings("unused")
        private String password = "";
        @SuppressWarnings("unused")
        private String clientToken = Ampl.getSettings().getInfoLauncher().getClientToken();

        Request() {

        }

        /**
         * Identifies which launcher is requesting an authentication. This is requested for Mojang accounts, where more
         * then one game accessible with the same account.
         */
        class Agent {

            @SuppressWarnings("unused")
            private String name = "Minecraft";
            @SuppressWarnings("unused")
            private int version = 1;
        }
    }

    /**
     * The class is the JSON structure for the succeeded authentication response of the Yggdrasil protocol. Any errors
     * need to be handled by another JSON-structure.
     */
    class Response {

        private String accesToken = "";
        private String clientToken = "";
        @SuppressWarnings("unused")
        private Profile[] availableProfiles = new Profile[]{new Profile()};
        private Profile selectedProfile = new Profile();

        /**
         * The method returns the chosen profile for Minecraft.
         *
         * @return a Profile object.
         */
        public Profile getSelectedProfile() {

            return selectedProfile;
        }

        /**
         * The method returns the same clientToken as used before.
         *
         * @return a String with the clientToken.
         */
        public String getClientToken() {

            return clientToken;
        }

        /**
         * The method returns the accesToken provided by the authentication server.
         *
         * @return a String with the accesToken.
         */
        public String getAccesToken() {

            return accesToken;
        }

        class Profile {

            private String id = "";
            private String name = "";
            private boolean legacy = false;

            /**
             * The method returns the UUID of the user.
             *
             * @return a String with the UUID.
             */
            public String getId() {

                return id;
            }

            /**
             * The method returns the in-game name of the user (not to be confused with the accounts username, which is
             * an email-address).
             *
             * @return a string with the in-game name.
             */
            public String getName() {

                return name;
            }

            /**
             * The method returns a boolean value to express the migration status of the account(Either Mojang or old
             * Minecraft). If the account was migrated this field wont appear in the response.
             *
             * @return a boolean value.
             */
            public boolean getLegacy() {

                return legacy;
            }
        }
    }
}
