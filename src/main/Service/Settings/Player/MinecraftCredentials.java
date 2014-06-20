package main.Service.Settings.Player;

import main.Interface.Log.LogWrapper;

import java.util.UUID;

/**
 * The class contains data, which will be stored inside a file in plaintext. Actually there is nothing risky. There is
 * no password, only a Token, which can be invalidated. Created by Max on 20.06.2014.
 */
public class MinecraftCredentials {

    private transient LogWrapper logger = LogWrapper.getLogger(MinecraftCredentials.class);
    private String accesToken = "";
    private String clienToken = "";
    private SelectedProfile selectedProfile = new SelectedProfile();

    /**
     * The method returns the accesToken for the game to authenticate the user. This number may change.
     *
     * @return a String containing the accesToken.
     */
    public String getAccesToken() {

        return accesToken;
    }

    /**
     * The method returns the clientToken, which is used to identify the client at the Ygdrasil-server of Mojang. If the
     * string is empty a random hexadecimal code will be generated.
     *
     * @return a string containing the clientToken.
     */
    public String getClienToken() {

        if (clienToken.isEmpty()) {
            logger.debug("Token is empty.<br> A new one will be generated.");
            clienToken = UUID.randomUUID().toString();
        }
        return clienToken;
    }

    /**
     * The method returns the selectedProfile object, which contains further information about the account.
     *
     * @return a selectedProfile object.
     */
    public SelectedProfile getSelectedProfile() {

        return selectedProfile;
    }
}
