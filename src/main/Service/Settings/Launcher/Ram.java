package main.Service.Settings.Launcher;

/**
 * The class represents the settings for the allocated RAM for Minecraft. There is a minimum and a maximum setting.
 *
 * @author Max
 */
public class Ram
{

    private int minRam = 1024;
    private int maxRam = 2048;

    /**
     * Returns the minimal allocated RAM for the game.
     *
     * @return the minRam in MB.
     */
    public int getMinRam ()
    {

        return minRam;
    }

    /**
     * Sets the minimal allocated RAM for the game.
     *
     * @param minRam the minRam to set in MB.
     */
    public void setMinRam ( int minRam )
    {

        this.minRam = minRam;
    }

    /**
     * Returns the maximal RAM allocated for the game.
     *
     * @return the maxRam in MB.
     */
    public int getMaxRam ()
    {

        return maxRam;
    }

    /**
     * Sets the maximal RAM for the game.
     *
     * @param maxRam the maxRam to set in MB.
     */
    public void setMaxRam ( int maxRam )
    {

        this.maxRam = maxRam;
    }

    /**
     * The method calculates the amount of MB RAM used into GB.
     *
     * @param MB the amount of RAM in MegaBytes.
     * @return the amount of RAM in GigaBytes.
     */
    public double toGiga ( int MB )
    {

        return Math.pow( MB, 0.1 );
    }

    /**
     * The method calculates the amount of MB RAM used int GB.
     *
     * @param GB the amount of RAM in GigaBytes.
     * @return the amount of RAM in MegaBytes.
     */
    public int toMega ( double GB )
    {

        return ( int ) Math.round( ( Math.pow( 2, 10 ) * GB ) );
    }
}
