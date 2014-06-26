package main.Interface.Launcher;

import main.Interface.Log.LogWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static main.Ampl.getSettings;

/**
 * The class represents the main frame of the application. It is composed of two panes, three buttons and one
 * combo-box.
 */
public class LauncherFrame extends JFrame implements ComponentListener,
        WindowListener {

    private static final long serialVersionUID = - 1443452893964454294L;
    private LogWrapper logger = LogWrapper.getLogger(LauncherFrame.class);

    /**
     * The constructor creates a new frame with settings taken from the configuration.
     */
    public LauncherFrame() {

        logger.debug("Building frame.<br> Setting stored properties.");
        setLocation(getSettings().getInfoLauncher().getLocation().getLocation());
        setTitle("AMP Launcher");
        Dimension MIN_SIZE = new Dimension(600, 400);
        setMinimumSize(MIN_SIZE);
        setSize(getSettings().getInfoLauncher().getSize());
        setMaximumSize(getSettings().getInfoLauncher().getSize());
        setResizable(true);
        BorderLayout LAYOUT = new BorderLayout();
        setLayout(LAYOUT);

        logger.debug("Properties set.<br> Adding panes.");

        bottomBar control = new bottomBar();
        add(control, BorderLayout.SOUTH);

        logger.debug("Adding listener.");
        addComponentListener(this);
        addWindowListener(this);
        logger.debug("Showing window on monitor " + getSettings().getInfoLauncher().getLocation().getGDevice() +
                "<br> at x: " + getSettings().getInfoLauncher().getLocation().getOffSet().getX() + "<br> y: " +
                getSettings().getInfoLauncher().getLocation().getOffSet().getY());
        setVisible(true);

    }

    /**
     * The method is called every time the frame is moved. It checks if the monitor gets changed.
     */
    private void defineMonitor() {

        Dimension screenEnd;
        screenEnd = new Dimension(
                getSettings().getInfoLauncher().getLocation().getGDev()[getSettings().getInfoLauncher().getLocation()
                        .getGDevice()]
                        .getDisplayMode().getWidth()
                        + getSettings().getInfoLauncher().getLocation().getGDev()[getSettings()
                        .getInfoLauncher()
                        .getLocation().getGDevice()]
                        .getDefaultConfiguration().getBounds().x,
                getSettings().getInfoLauncher().getLocation().getGDev()[getSettings().getInfoLauncher().getLocation()
                        .getGDevice()]
                        .getDisplayMode().getHeight()
                        + getSettings().getInfoLauncher().getLocation().getGDev()[getSettings()
                        .getInfoLauncher()
                        .getLocation().getGDevice()]
                        .getDefaultConfiguration().getBounds().y);

        Dimension screenStart = new Dimension(
                getSettings().getInfoLauncher().getLocation().getGDev()[getSettings().getInfoLauncher().getLocation()
                        .getGDevice()]
                        .getDefaultConfiguration().getBounds().x,
                getSettings().getInfoLauncher().getLocation().getGDev()[getSettings().getInfoLauncher().getLocation()
                        .getGDevice()]
                        .getDefaultConfiguration().getBounds().y);

        if (this.getLocationOnScreen().x > screenEnd.width
                || this.getLocationOnScreen().y > screenEnd.height
                || this.getLocationOnScreen().x < screenStart.width
                || this.getLocationOnScreen().y < screenStart.height) {
            logger.debug("Entered new monitor.");
            for (int i = 0; i < getSettings().getInfoLauncher().getLocation().getGDev().length; i++) {
                if (this.getLocationOnScreen().x < getSettings().getInfoLauncher().getLocation().getGDev()[i]
                        .getDisplayMode().getWidth()
                        + getSettings().getInfoLauncher().getLocation().getGDev()[i].getDefaultConfiguration()
                        .getBounds().x
                        && this.getLocationOnScreen().y < getSettings().getInfoLauncher().getLocation()
                        .getGDev()[i].getDisplayMode().getHeight()
                        + getSettings().getInfoLauncher().getLocation().getGDev()[i]
                        .getDefaultConfiguration().getBounds().y
                        && this.getLocationOnScreen().x > getSettings().getInfoLauncher().getLocation()
                        .getGDev()[i].getDefaultConfiguration()
                        .getBounds().x
                        && this.getLocationOnScreen().x > getSettings().getInfoLauncher().getLocation()
                        .getGDev()[i].getDefaultConfiguration()
                        .getBounds().x) {
                    getSettings().getInfoLauncher().getLocation().setGDevice(i);
                    getSettings().getInfoLauncher().getLocation().setScreen();
                    logger.debug("Monitor changed: <br> New screen is device" + i);
                }
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        /*
        Nothing happens here
         */

    }

    @Override
    public void componentMoved(ComponentEvent e) {

        logger.debug("Checking monitor.");
        defineMonitor();
    }

    @Override
    public void componentShown(ComponentEvent e) {

        /*
        Nothing happens here.
         */
    }

    @Override
    public void componentHidden(ComponentEvent e) {

        /*
        Nothing happens here.
         */
    }

    @Override
    public void windowOpened(WindowEvent e) {

        /*
        Nothing happens here.
         */
    }

    @Override
    public void windowClosing(WindowEvent e) {

        /* TODO we have to implement this reaction based on the state of Minecraft. Elsewhere we won't be able to
        close the application. */
        this.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {

        /*
        Nothing happens here.
         */
    }

    @Override
    public void windowIconified(WindowEvent e) {

        /*
        Nothing happens here
         */
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

        /*
        Nothing happens here
         */
    }

    @Override
    public void windowActivated(WindowEvent e) {

        /*
        Nothing happens here
         */
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

        /*
        Nothing happens here
         */
    }
}

