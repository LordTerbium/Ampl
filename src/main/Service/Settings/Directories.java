package main.Service.Settings;

import main.Interface.Log.LogWrapper;

import java.io.File;

/**
 * The class contains all paths used by the application.
 *
 * @author Max
 */
public class Directories {

    private LogWrapper logger = LogWrapper.getLogger(Directories.class);
    private File home;
    private File instances, file;
    private File data, backup;
    private File world, config;
    private File user, pack, images;

    /**
     * The constructor activates the directory three and checks if any of them is missing. It also stores them based on
     * the underlying OS.
     */
    protected Directories() {

        logger.debug("Setting up directories variables.");
        logger.debug("Checking OS type.");
        String os = System.getProperty("os.name");

        if (os.startsWith("Windows")) {
            logger.debug("Detected Windows.<br> Files will be stored inside \"%APPDATA%\\Ampl\" .");
            home = new File(System.getenv("APPDATA") + File.separator + ".Ampl");
        } else {
            logger.debug("Detected other Unix system.<br> Files will be stored inside \'\"user.home\"\\Ampl\'.");
            home = new File(System.getProperty("user.home") + File.separator
                    + ".Ampl");
        }

        instances = new File(home.getAbsolutePath() + File.separator
                + "instances");
        if (! instances.exists()) {
            logger.config("Creating " + instances.getName());
            instances.mkdirs();
        }

        file = new File(home.getAbsolutePath() + File.separator + "file");
        if (! file.exists()) {
            logger.config("Creating " + file.getName());
            file.mkdirs();
        }

        data = new File(file.getAbsolutePath() + File.separator + "data");
        if (! data.exists()) {
            logger.config("Creating " + data.getName());
            data.mkdirs();
        }

        backup = new File(file.getAbsolutePath() + File.separator + "backup");
        if (! backup.exists()) {
            logger.config("Creating " + backup.getName());
            backup.mkdirs();
        }

        world = new File(backup.getAbsolutePath() + File.separator + "world");
        if (! world.exists()) {
            logger.config("Creating " + world.getName());
            world.mkdirs();
        }

        config = new File(backup.getAbsolutePath() + File.separator + "config");
        if (! config.exists()) {
            logger.config("Creating " + config.getName());
            config.mkdirs();
        }

        user = new File(data.getAbsolutePath() + File.separator + "user");
        if (! user.exists()) {
            logger.config("Creating " + user.getName());
            user.mkdirs();
        }

        pack = new File(data.getAbsolutePath() + File.separator + "pack");
        if (! pack.exists()) {
            logger.config("Creating " + pack.getName());
            pack.mkdirs();
        }

        images = new File(data.getAbsolutePath() + File.separator + "images");
        if (! images.exists()) {
            logger.config("Creating" + images.getName());
            images.mkdirs();
        }

        logger.debug("Finished setting up directories variables.<br> Things will be stored in "
                + home.getAbsolutePath());

    }

    /**
     * Returns the standard directory as a file object.
     *
     * @return the home
     */
    public File getHome() {

        return home;
    }

    /**
     * Returns the standard directory as a file object.
     *
     * @return the home
     */
    public File getInstances() {

        return instances;
    }

    /**
     * Returns the standard directory as a file object.
     *
     * @return the home
     */
    public File getFile() {

        return file;
    }

    /**
     * Returns the standard directory as a file object.
     *
     * @return the home
     */
    public File getData() {

        return data;
    }

    /**
     * Returns the backup directory as a file object.
     *
     * @return the home
     */
    public File getBackup() {

        return backup;
    }

    /**
     * Returns the world-backup directory as a file object.
     *
     * @return the home
     */
    public File getWorld() {

        return world;
    }

    /**
     * Returns the config-backup directory as a file object.
     *
     * @return the home
     */
    public File getConfig() {

        return config;
    }

    /**
     * Returns the user-data directory as a file object.
     *
     * @return the home
     */
    public File getUser() {

        return user;
    }

    /**
     * Returns the pack-data directory as a file object.
     *
     * @return the home
     */
    public File getPack() {

        return pack;
    }

    /**
     * Returns the images directory as a file object.
     *
     * @return the home
     */
    public File getImages() {

        return images;
    }
}
