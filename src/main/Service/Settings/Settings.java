package main.Service.Settings;

import main.Interface.Log.LogWrapper;
import main.Service.Settings.Player.Accounts;

/**
 * The class contains mostly important information that is used across the entire application. Here everything is stored
 * and later on saved into files.
 *
 * @author Max
 */
public class Settings {

    private LogWrapper logger = LogWrapper.getLogger(Settings.class);
    private InfoLauncher infoLauncher;
    private Directories directories;
    private Accounts accounts;

    /**
     * The constructor creates a new setting instances by loading them all
     */
    public Settings() {

        logger.debug("Loading launcher settings.");

        logger.debug("Setting up directories for storing data.");
        directories = new Directories();

        logger.debug("Setting up launcher properties.");
        infoLauncher = new InfoLauncher(directories);
        infoLauncher.load();

        logger.debug("Setting up user data.");
        accounts = new Accounts();
    }

    /**
     * Returns the settings of the launcher
     *
     * @return the infoLauncher
     */
    public InfoLauncher getInfoLauncher() {

        return infoLauncher;
    }

    /**
     * Returns the directory three with all directories.
     *
     * @return the directory three.
     */
    public Directories getDirectories() {

        return directories;
    }

    /**
     * The method guaranties access to the user data.
     *
     * @return a Accounts object.
     */
    public Accounts getAccounts() {

        return accounts;
    }
}
