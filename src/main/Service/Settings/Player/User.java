package main.Service.Settings.Player;

import main.Interface.Log.LogWrapper;

/**
 * The class represents a single user, which can be used to launch the game. Inside here we have the local stored
 * information, the temporarily saved authentication data (like password), and some data about the skin.
 *
 * @author Max
 */
public class User {

    private transient LogWrapper logger = LogWrapper.getLogger(User.class);
    private boolean mode = true;
    private transient UserCredentials userCredentials = new UserCredentials();
    private MinecraftCredentials minecraftCredentials = new MinecraftCredentials();

    public MinecraftCredentials getMinecraftCredentials() {

        return minecraftCredentials;
    }
}
