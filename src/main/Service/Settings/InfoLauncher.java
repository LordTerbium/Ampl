package main.Service.Settings;

import main.Interface.Log.LogWrapper;
import main.Json.JsonUtilities;
import main.Service.Settings.Launcher.Location;
import main.Service.Settings.Launcher.PermGen;
import main.Service.Settings.Launcher.Ram;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * The class contains variables for the settings of the launcher. From here the "settings.json" are de/serialized.
 *
 * @author Max
 */
public class InfoLauncher {

    private transient static final LogWrapper logger = LogWrapper
            .getLogger(InfoLauncher.class);
    private transient Directories directories;
    private Location location = new Location();
    private Dimension size = new Dimension(500, 500);
    private Ram ram = new Ram();
    private PermGen permGen = new PermGen();
    private int logLevel = 800;
    private int keepLauncherOpen = 0;
    private List<String> javaArgs = new ArrayList<String>();

    protected InfoLauncher(Directories directories) {

        this.directories = directories;
    }

    /**
     * Returns the position of the launcher window and of the Minecraft window. This class contains no direct
     * coordinates. It stores the monitor and the coordinates from his border.
     *
     * @return the location as a Location instance. To get the coordinates you have to get the values contained in the
     * class.
     */
    public Location getLocation() {

        return location;
    }

    /**
     * Returns the width and height of the launcher.
     *
     * @return the size as a Dimension.
     */
    public Dimension getSize() {

        return size;
    }

    /**
     * The method sets a new Dimension object of the launcher height and width.
     *
     * @param size the Dimension object with the height and width of the launcher.
     */
    public void setSize(Dimension size) {

        this.size = size;
    }

    /**
     * Returns the RAM object containing information of the allocated minimal and maximal RAM for Minecraft.
     *
     * @return the RAM object.
     */
    public Ram getRam() {

        return ram;
    }

    /**
     * Returns the permGen object containing information of the allocated minimal and maximal RAM for Minecraft.
     *
     * @return the permGen object.
     */
    public PermGen getPermGen() {

        return permGen;
    }

    /**
     * Returns the stored log level for the rootLogger.
     *
     * @return the Level object for the rootLogger.
     */
    public Level getLogLevel() {

        return Level.parse(Integer.toString(logLevel));
    }

    /**
     * Sets the logging level of the rootLogger to be stored.
     *
     * @param logLevel the Level object.
     */
    public void setLogLevel(Level logLevel) {

        this.logLevel = logLevel.intValue();
    }

    /**
     * Returns the stored behavior of the launcher when Minecraft was started. 0 means "keep open", 1 means "close when
     * starting", 2 means "close by start and open after closing the game.
     *
     * @return the int value of the behavior.
     */
    public int getKeepLauncherOpen() {

        return keepLauncherOpen;
    }

    /**
     * Sets the behavior of the launcher after starting Minecraft. 0 means "keep open", 1 means "close when starting", 2
     * means "close by start and open after closing the game.
     *
     * @param keepLauncherOpen the behavior of the launcher to store.
     */
    public void setKeepLauncherOpen(int keepLauncherOpen) {

        this.keepLauncherOpen = keepLauncherOpen;
    }

    /**
     * Returns a list with all stored javaArguments for the JVM of the game.
     *
     * @return the list object containing the javaArguments.
     */
    public List<String> getJavaArgs() {

        return javaArgs;
    }

    /**
     * Sets the List with the javaArguments for the JVM of the game.
     *
     * @param javaArgs the list object to set.
     */
    public void setJavaArgs(List<String> javaArgs) {

        this.javaArgs = javaArgs;
    }

    public void save() {

        logger.info("Saving data.");
        logger.debug("Saving launcher config to " + directories.getInstances().getAbsolutePath());
        JsonUtilities.save(this, directories.getInstances().getAbsolutePath() + File.separator + "settigns.conf");
    }

    public void load() {

        logger.info("Loading data.");
        logger.debug("Loading launcher config from " + directories.getInstances().getAbsolutePath());

        if (! new File(directories.getInstances().getAbsolutePath() + File.separator + "settigns.conf").exists()) {
            logger.info("Creating settings.conf");
            logger.debug("Loading first time.<br> New file will be created.");

            try {
                new File(directories.getInstances().getAbsolutePath() + File.separator + "settigns.conf")
                        .createNewFile();
            } catch (IOException e) {
                logger.fatal("Couldn't create a file: " + e.getMessage());
            }
            logger.debug("Saving data for first time.");
            save();
        } else {
            JsonUtilities.load(this, directories.getInstances().getAbsolutePath() + File.separator + "settigns.conf");
        }
    }
}
