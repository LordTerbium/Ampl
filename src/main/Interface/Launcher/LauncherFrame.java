package main.Interface.Launcher;

import main.Interface.Log.LogWrapper;
import main.Service.Settings.InfoLauncher;
import main.Service.Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The class represents the main frame of the application. It is composed of two panes, three buttons and one combo-box.
 */
public class LauncherFrame extends JFrame implements ComponentListener, WindowListener
{

    private static final long          serialVersionUID = - 1443452893964454294L;
    private static       LauncherFrame _instance        = new LauncherFrame();
    private              LogWrapper    logger           = LogWrapper.getLogger( LauncherFrame.class );

    /**
     * The constructor creates a new frame with settings taken from the configuration.
     */
    private LauncherFrame ()
    {

        logger.debug( "Building frame.<br> Setting stored properties." ); setLocation( InfoLauncher.instance().getLocation().getLocation() ); System.out.println( InfoLauncher.instance().getLocation().getLocation().toString() );
        setTitle( "AMP Launcher" );
        Dimension MIN_SIZE = new Dimension( 600, 400 );
        setMinimumSize( MIN_SIZE ); setSize( InfoLauncher.instance().getSize() ); setMaximumSize( InfoLauncher.instance().getSize() );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( true );
        BorderLayout LAYOUT = new BorderLayout();
        setLayout( LAYOUT );

        logger.debug( "Properties set.<br> Adding panes." );

        bottomBar control = new bottomBar();
        add( control, BorderLayout.SOUTH );

        logger.debug( "Adding listener." );
        addComponentListener( this );
        addWindowListener( this ); logger.debug( "Showing window on monitor " + InfoLauncher.instance().getLocation().getGDevice() +
                                                 "<br> at x: " + InfoLauncher.instance().getLocation().getOffSet().getX() + "<br> y: " +
                                                 InfoLauncher.instance().getLocation().getOffSet().getY() );
        setVisible( true );

    }

    /**
     * Static method that returns the instance of the launcher frame.
     *
     * @return a LauncherFrame object.
     */
    public static LauncherFrame instance ()
    {
        return _instance;
    }

    @Override
    public void componentResized ( ComponentEvent e ) {}

    @Override
    public void componentMoved ( ComponentEvent e )
    {

        logger.debug( "Checking monitor." );
        defineMonitor();
    }

    /**
     * The method is called every time the frame is moved. It checks if the monitor gets changed.
     */
    private void defineMonitor ()
    {

        GraphicsDevice GDev = InfoLauncher.instance().getLocation().getGDev()[ InfoLauncher.instance().getLocation().getGDevice() ]; System.out.println( InfoLauncher.instance().getLocation().getGDevice() );
        Dimension screenEnd = new Dimension( GDev.getDisplayMode().getWidth() + GDev.getDefaultConfiguration().getBounds().x, GDev.getDisplayMode().getHeight() + GDev.getDefaultConfiguration().getBounds().y );

        Dimension screenStart = new Dimension( GDev.getDefaultConfiguration().getBounds().x, GDev.getDefaultConfiguration().getBounds().y );

        if ( this.getLocationOnScreen().x > screenEnd.width || this.getLocationOnScreen().y > screenEnd.height ||
             this.getLocationOnScreen().x < screenStart.width ||
             this.getLocationOnScreen().y < screenStart.height )
        {
            logger.debug( "Entered new monitor." ); for ( int i = 0; i < InfoLauncher.instance().getLocation().getGDev().length; i++ )
        {
            if ( this.getLocationOnScreen().x < InfoLauncher.instance().getLocation().getGDev()[ i ].getDisplayMode().getWidth() + InfoLauncher.instance().getLocation().getGDev()[ i ].getDefaultConfiguration().getBounds().x &&
                 this.getLocationOnScreen().y < InfoLauncher.instance().getLocation().getGDev()[ i ].getDisplayMode().getHeight() + InfoLauncher.instance().getLocation().getGDev()[ i ].getDefaultConfiguration().getBounds().y &&
                 this.getLocationOnScreen().x > InfoLauncher.instance().getLocation().getGDev()[ i ].getDefaultConfiguration().getBounds().x &&
                 this.getLocationOnScreen().x > InfoLauncher.instance().getLocation().getGDev()[ i ].getDefaultConfiguration().getBounds().x )
            {
                InfoLauncher.instance().getLocation().setGDevice( i ); InfoLauncher.instance().getLocation().setScreen();
                    logger.debug( "Monitor changed: <br> New screen is device" + i );
                }
            }
        }
    }

    @Override
    public void componentShown ( ComponentEvent e ) {}

    @Override
    public void componentHidden ( ComponentEvent e ) {}

    @Override
    public void windowOpened ( WindowEvent e ) {}

    @Override
    public void windowClosing ( WindowEvent e )
    {

        /* TODO we have to implement this reaction based on the state of Minecraft. Elsewhere we won't be able to
        close the application. */
        this.setVisible( false ); Settings.saveAll();
    }

    @Override
    public void windowClosed ( WindowEvent e ) {}

    @Override
    public void windowIconified ( WindowEvent e ) {}

    @Override
    public void windowDeiconified ( WindowEvent e ) {}

    @Override
    public void windowActivated ( WindowEvent e ) {}

    @Override
    public void windowDeactivated ( WindowEvent e ) {}
}

