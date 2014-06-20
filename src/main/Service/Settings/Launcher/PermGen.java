package main.Service.Settings.Launcher;

/**
 * The class represents the settings for the allocated PermGen for Minecraft. There is a minimum and a maximum setting.
 *
 * @author Max
 */
public class PermGen {

    private int minGen = 1024;
    private int maxGen = 2048;

    /**
     * Returns the minimal allocated PermGen for the game.
     *
     * @return the minGen in MB.
     */
    public int getMinGen() {

        return minGen;
    }

    /**
     * Sets the minimal allocated PermGen for the game.
     *
     * @param minGen the minRam to set in MB.
     */
    public void setMinGen(int minGen) {

        this.minGen = minGen;
    }

    /**
     * Returns the maximal PermGen allocated for the game.
     *
     * @return the maxGen in MB.
     */
    public int getMaxGen() {

        return maxGen;
    }

    /**
     * Sets the maximal PermGen for the game.
     *
     * @param maxGen the maximal PermGen to set in MB.
     */
    public void setMaxGen(int maxGen) {

        this.maxGen = maxGen;
    }
}
