package main.Service.Settings;

import main.Service.Settings.Player.Accounts;

/**
 * The class contains mostly important information that is used across the entire application. Here everything is stored and later on saved into files.
 *
 * @author Max
 */
public class Settings
{

    public static void loadAll ()
    {

        Directories.instance(); InfoLauncher.instance().load(); Accounts.instance().load();
    }


    /**
     * The method is called before stopping the launcher. All settings get saved.
     */
    public static void saveAll ()
    {

        Accounts.instance().save(); InfoLauncher.instance().save();
    }
}
