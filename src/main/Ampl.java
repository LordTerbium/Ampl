package main;

import main.Interface.Log.Console;
import main.Interface.Log.LogWrapper;
import main.Service.Settings.Settings;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.io.IOException;

public class Ampl {

    public static Console logConsole = new Console(new Point(500, 500));
    public static Settings settings;

    public static void main(String[] args) throws IOException {

        LogWrapper.setupLogger();
        LogWrapper logger = LogWrapper.getLogger(Ampl.class);
        logger.debug("<bold>Logging initiated</bold>, we can be sure that nothing gets lost.");
        logger.debug("Opening console ...");
        logConsole.setVisible(true);
        logger.debug("Opened console.<br> This should be the first message on the Console.");
        System.out.println("Jetzt ist das Fenster offen");

        logger.debug("Loading settings.");
        settings = new Settings();
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("I couldn't find the Nimbus Look&Feel. Switching back to system standard.");
            logger.debug("The problem: <br>" + e.getLocalizedMessage());
        }

        // Gui is painted by the EDT

		/*SwingUtilities.invokeLater(new Runnable() {
            public void run() {

				frame = new LauncherFrame();

			}
		});*/

    }

}
