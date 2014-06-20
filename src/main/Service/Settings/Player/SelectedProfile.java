package main.Service.Settings.Player;

import main.Interface.Log.LogWrapper;

/**
 * In future version of Mojang's authentication method there will be the possibility to manage more profiles from one
 * account. This is the selected account for the game, which the accesToken was created for. Created by Max on
 * 20.06.2014.
 */
public class SelectedProfile {

    private LogWrapper logger = LogWrapper.getLogger(SelectedProfile.class);
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
     * The method returns the in-game name of the user (not to be confused with the accounts username, which is an
     * email-address).
     *
     * @return a string with the in-game name.
     */
    public String getName() {

        return name;
    }

    /**
     * The method returns a boolean value to express the migration status of the account(Either Mojang or old
     * Minecraft).
     *
     * @return a boolean value.
     */
    public boolean getLegacy() {

        return legacy;
    }
}
