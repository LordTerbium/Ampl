package main.Service.Settings.Player;

/**
 * The class represents a single user, which can be used to launch the game. Inside here we have the local stored
 * information, the temporarily saved authentication data (like password), and some data about the skin.
 *
 * @author Max
 */
public class User {

    private boolean mode = true;
    private String accesToken = "";
    private String id = "";
    private String name = "";
    private boolean legacy = false;

    public User(String name) {

        this.name = name;
    }

    /**
     * The method returns the UUID of the user.
     *
     * @return a String with the UUID.
     */
    public String getId() {

        return id;
    }

    /**
     * The method sets the unique UUID of the player. This might get important later on, where Mojang will allow more
     * profiles for account.
     *
     * @param id is a String containing a hexadecimal code which identifies the player.
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * The method returns the in-game name of the user (not to be confused with the accounts username, which is an
     * email-address).
     *
     * @return a string with the in-game name.
     */
    public String getName() {

        return name;
    }

    /**
     * The method sets the name of the player ingame. If the accout wasn't migrated, this value equals to the username.
     *
     * @param name is a String containing the ingame name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * The method returns a boolean value to express the migration status of the account(Either Mojang or old
     * Minecraft).
     *
     * @return a boolean value.
     */
    public boolean isLegacy() {

        return legacy;
    }

    /**
     * The methods sets status of the account. If true, the account hasn't been migrated yet. Otherwise the value should
     * default to false.
     *
     * @param legacy is a boolean value specifing the status of the account.
     */
    public void setLegacy(boolean legacy) {

        this.legacy = legacy;
    }

    /**
     * The method returns the accesToken for the game to authenticate the user. This number may change.
     *
     * @return a String containing the accesToken.
     */
    public String getAccesToken() {

        return accesToken;
    }

    /**
     * The method sets the accesToken used to properly start the game.
     *
     * @param accesToken is a String with a hexadecimal code inside.
     */
    public void setAccesToken(String accesToken) {

        this.accesToken = accesToken;
    }

    /**
     * The method returns the mode.
     *
     * @return a boolean value. Mostly true.
     */
    public boolean isMode() {

        return mode;
    }

    /**
     * The method sets the mode.
     *
     * @param mode is a boolean value.
     */
    public void setMode(boolean mode) {

        this.mode = mode;
    }
}
