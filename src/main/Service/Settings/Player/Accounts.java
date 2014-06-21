package main.Service.Settings.Player;

import main.Ampl;
import main.Interface.Log.LogWrapper;
import main.Json.JsonUtilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains all Minecraft-Accounts.
 *
 * @author Max
 */
public class Accounts {

    private LogWrapper logger = LogWrapper.getLogger(Accounts.class);
    private List<User> userList = new ArrayList<User>();

    /**
     * The method returns the user list.
     *
     * @return a List<User> object.
     */
    public List<User> getUserList() {

        return userList;
    }

    /**
     * The method returns a user by matching the given in-game name with the stored data. If an account contains the
     * wanted string, the method will return that user instance. Else it returns null.
     *
     * @param playerName is the wanted in-game name
     * @return a User object that corresponds to the search string. Elsewhere it returns null.
     */
    public User getUserByName(String playerName) {

        for (User user : userList) {
            if (user.getMinecraftCredentials().getSelectedProfile().getName().equals(playerName)) {
                return user;
            }
        }
        return null;
    }

    public void save() {

        logger.info("Saving user data.");
        logger.debug("Saving user data to " + Ampl.getSettings().getDirectories().getUser().getAbsolutePath());
        JsonUtilities
                .save(userList,
                        Ampl.getSettings().getDirectories().getUser().getAbsolutePath() + File.separator + "user.dat");
    }

    public void load() {

        logger.info("Loading user credentials.");
        logger.debug("Loading data from " + Ampl.getSettings().getDirectories().getUser().getAbsolutePath());
        if (! new File(Ampl.getSettings().getDirectories().getUser() + File.separator + "user.dat").exists()) {
            logger.debug("Loading data for first time. Creating new file.");
            try {
                new File(Ampl.getSettings().getDirectories().getUser().getAbsolutePath() + File.separator + "user.dat")
                        .createNewFile();
            } catch (IOException e) {
                logger.fatal("Couldn't create a file: " + e.getMessage());
            }
            logger.debug("File created. Saving data.");
            save();
        } else {
            JsonUtilities.load(userList,
                    Ampl.getSettings().getDirectories().getUser().getAbsolutePath() + File.separator + "user.dat");
            logger.debug("Loaded data.");
        }
    }
}
