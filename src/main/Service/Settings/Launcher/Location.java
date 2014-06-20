package main.Service.Settings.Launcher;

import main.Interface.Log.LogWrapper;

import java.awt.*;

/**
 * The class contains variables that define the position of the Launcher and Minecraft-Window if you have more monitors.
 * It also auto-saves the changing of the position.
 *
 * @author Max
 */
public class Location {

    private transient LogWrapper logger = LogWrapper.getLogger(Location.class);
    private Point offSet = new Point(0, 0);
    private transient Point location = new Point(0, 0);
    private int GDevice = 0;
    private transient GraphicsEnvironment GEnv = GraphicsEnvironment
            .getLocalGraphicsEnvironment();
    private transient GraphicsDevice[] GDev = GEnv.getScreenDevices();

    /**
     * Returns the offset on the currently used monitor. This are not the positional coordinates from the upper left
     * corner of the main monitor.
     *
     * @return the offSet
     */
    public Point getOffSet() {

        return offSet;
    }

    /**
     * Sets the location on the screen, which is currently used. So for example, if we had a monitor on the right of our
     * standard monitor and this device had the id = 1, we could set the offset starting by the begin of the second
     * monitor. This is more intuitive because we are seeing two separated screens.
     *
     * @param offSet the offSet to set
     */
    public void setOffSet(Point offSet) {

        this.offSet = offSet;
    }

    /**
     * Returns the monitor where the frame will be opened.
     *
     * @return the gDev
     */
    public int getGDevice() {

        return GDevice;
    }

    /**
     * Used to change the monitor where the frames are shown the next time the launcher starts.
     *
     * @param gDev the gDev to set
     */
    public void setGDevice(int gDev) {

        GDevice = gDev;
    }

    /**
     * Returns a list of all screens installed on the computer.
     *
     * @return the gDev
     */
    public GraphicsDevice[] getGDev() {

        return GDev;
    }

    /**
     * Returns the full positional coordinates starting from the upper left corner of the main monitor.
     *
     * @return the location
     */
    public Point getLocation() {

        return location;
    }

    /**
     * Sets the full positional coordinates starting from the upper left corner of the main monitor.
     *
     * @param location the location to set
     */
    public void setLocation(Point location) {

        this.location = location;
    }

    /**
     * This method refreshes the absolute coordinates of the frame by taking the provided device-id and adding the
     * chosen offset from the user. If the device is not found, it will default to the main monitor. If even this
     * doesn't work, the program stops.
     */
    public void setScreen() {

        if (GDevice > - 1 && GDevice < GDev.length) {
            location.setLocation(
                    (GDev[GDevice].getDefaultConfiguration().getBounds().x + (GDev[GDevice]
                            .getDisplayMode().getWidth()) / 2) - offSet.x,
                    (GDev[GDevice].getDefaultConfiguration().getBounds().y + GDev[GDevice]
                            .getDisplayMode().getHeight() / 2) - offSet.y);
            logger.debug("Changed starting position of Launcher to<br> x: " + location.x + "<br> y: " + location.y);
        } else if (GDev.length > 0) {
            location.setLocation(
                    GDev[0].getDefaultConfiguration().getBounds().x
                            + (GDev[0].getDisplayMode().getWidth()) / 2,
                    GDev[0].getDefaultConfiguration().getBounds().y
                            + (GDev[0].getDisplayMode().getHeight()) / 2);
            logger.error(
                    "Couldn't find the requested <bold>monitor</bold>.<br>I'm going to start the Launcher in the " +
                            "default monitor.");
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }
}
