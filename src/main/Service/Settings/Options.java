/*
package main.Service.Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Interface.Log.Console;
import main.Interface.Log.ConsoleHandler;
import main.Interface.Log.HtmlFormatter;
import main.Interface.Log.LogWrapper;
import main.Json.loginJSON.ftpData;
import main.Json.packJSON.Instance.Build;
import main.Json.packJSON.Instance.Pack;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Options {

    private LogWrapper logger = LogWrapper.getLogger(Options.class);
    // Directories
    private File home, files, instances, data, modRepo, fileRepo, legacyMods,
            legacyConfigs, minecraftVersions, forgeVersions, packs, temp;

    private List<File> mcVersions, fgVersions, instanceList;

    // Files
    private File options, modList, configList, disponiblePacks;

    @SuppressWarnings("unused")
    private File[] fileList = {options, modList, configList};

    private String vJson, assJson, ftpJson, logFile, resources, mods, configs,
            assets, libraries, versions;

    // List of all packs
    private List<Object> packList = new ArrayList<Object>();

    // Settings of the launcher

    // Support for two ore more Monitors
    private GraphicsEnvironment GEnv = GraphicsEnvironment
            .getLocalGraphicsEnvironment();
    private GraphicsDevice[] GDev = GEnv.getScreenDevices();
    private Point location = new Point(0, 0);

    // Instances which are created
    private Console console;
    private ftpData serverLogin;

    // Logger
    public Logger logger1;
    private FileHandler fileTxt;
    private SimpleFormatter formatterTxt;
    private ConsoleHandler consoleHtml;
    private HtmlFormatter formatterHtml;

    // User based Information

    // Version of the Launcher
    private int[] version = {0, 0};
    // Build Type
    private int build = Build.WIP.ordinal();
    */
/*
     * If the Console should either be shown or not
     *//*

    private boolean showConsole = true;
    */
/*
     * Which messages should be printed out
     *//*

    private int logLevel = Level.INFO.intValue();
    */
/*
     * Parameter for the MC Process, which determinates the forge debug level
     *//*

    private int forgeLogLevel = Level.INFO.intValue();
    */
/*
     * If there is a message with a higher LogLevel, then this, the Console will
     * be shown
     *//*

    private int showConsoleByLevel = Level.WARNING.intValue();
    // Ram Parameter
    private int maxRam = 1024;
    // PermGen Parameter
    private int maxPermGen = 128;
    */
/*
     * Window size of the Launcher
     *//*

    private Dimension size = new Dimension(800, 500);
    // The monitor where the launcher is opened
    private int GDevice = 0;
    // If MC should start maximised
    private boolean maximisedMc = false;
    // User based Arguments/Parameters
    private String javaArgs = "";
    */
/*
     * The Tab with the pack which was selected the last time the programm was
     * run
     *//*

    private int selectedPack;

    */
/**
 * Storage for all options
 *//*

    public Options() {
        //logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try {
            fileTxt = new FileHandler("Log.log");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        formatterHtml = new HtmlFormatter();
        consoleHtml = new ConsoleHandler();
        consoleHtml.setFormatter(formatterHtml);
        //logger.setUseParentHandlers(true);
        //logger.addHandler(fileTxt);
        setupFiles();
        loadProperties();
        //logger.setLevel(Level.parse(Integer.toString(logLevel)));
        setScreen();
        initConsole();
        //logger.addHandler(consoleHtml);
    }

    */
/**
 * Checks all GraphicDevices connencted to the pc and selects where the
 * Launcher will be opened
 *//*

    public void setScreen() {

        if (GDevice > -1 && GDevice < GDev.length) {
            location.setLocation(
                    (GDev[GDevice].getDefaultConfiguration().getBounds().x + (GDev[GDevice]
                            .getDisplayMode().getWidth()) / 2) - size.width / 2,
                    (GDev[GDevice].getDefaultConfiguration().getBounds().y + GDev[GDevice]
                            .getDisplayMode().getHeight() / 2)
                            - size.height
                            / 2);
        } else if (GDev.length > 0) {
            location.setLocation(
                    GDev[0].getDefaultConfiguration().getBounds().x
                            + (GDev[0].getDisplayMode().getWidth()) / 2,
                    GDev[0].getDefaultConfiguration().getBounds().y
                            + (GDev[0].getDisplayMode().getHeight()) / 2);
            //logger.warning("Couldn't find the requested <bold>monitor</bold>.<br>I'm going to start the Launcher in the default monitor.");
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    */
/**
 * Sets all variables with the files
 *//*

    private void setupFiles() {

        // Setting up directories
        home = new File(System.getProperty("user.dir"));
        files = new File(home, "files");
        instances = new File(home, "instances");
        data = new File(files, "data");
        modRepo = new File(files, "modRepo");
        fileRepo = new File(files, "fileRepo");
        temp = new File(files, "temp");
        legacyMods = new File(modRepo, "mods");
        legacyConfigs = new File(modRepo, "configs");
        minecraftVersions = new File(fileRepo, "minecraftVersions");
        forgeVersions = new File(fileRepo, "forgeVersions");
        packs = new File(data, "packs");

        // Setting up files
        options = new File(home, "options.json");
        modList = new File(data, "modList.json");
        configList = new File(data, "configList.json");

        checkFiles();

        // Setting up file lists
        mcVersions = Arrays.asList(minecraftVersions.listFiles());
        fgVersions = Arrays.asList(forgeVersions.listFiles());
        instanceList = Arrays.asList(instances.listFiles());

        // Setting up abstract directories
        libraries = "libraries";
        assets = "assets";
        versions = "versions";
        resources = "resourcepacks";
        mods = "mods";
        configs = "config";

        // Setting up abstract files
        vJson = "version.json";
        assJson = "legacy.json";
        ftpJson = "ftp.json";

        logger.info("Finished setting up files.");

    }

    */
/**
 * Sets the global logger
 *//*

    public void initConsole() {
        console = new Console(location);
        console.setVisible(true);
        logger.info("Opened Console.");
    }

    */
/**
 * Refreshes all variables containing files
 *
 * @return true if everything was found
 *//*

    public void refreshFiles() {
        setupFiles();
    }

    */
/**
 * Checks if directories exist. If not it will attempt to create it.
 *//*

    public void checkFiles() {

        File[] dirList = {home, files, instances, data, modRepo, fileRepo,
                legacyMods, legacyConfigs, minecraftVersions, forgeVersions,
                packs, temp};
        for (File f : dirList) {
            if (!f.exists()) {
                logger.config("Missing a directory: " + f.getName()
                        + "</br> Will create one");
                try {
                    f.mkdirs();
                } catch (Exception e) {
                    //logger.severe("You are executing the launcher in a location without <bold>write permission</bold>.<br>Please solve this.");
                    String[] options = {"OK"};
                    JOptionPane
                            .showOptionDialog(
                                    null,
                                    "<html><center>Cannot create a missing file.<br/><br/>Make sure"
                                            + " you are not running the Launcher from somewhere with<br/>write"
                                            + " permissions for your user account such as your Home/Users folder"
                                            + " or desktop.</center></html>",
                                    "Warning", JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE, null, options,
                                    options[0]);
                    System.exit(0);
                }
            }
        }
    }

    */
/**
 * Method invokes the reading of the options file.
 *//*

    public void loadProperties() {

        Gson gson = new Gson();
        if (options.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(
                        options.getAbsolutePath()));
                JsonObject json = (JsonObject) new JsonParser().parse(br);

                // Value Assignment
                version = gson.fromJson(json.getAsJsonArray("servers"),
                        int[].class);
                build = json.get("build").getAsInt();
                showConsole = json.get("showConsole").getAsBoolean();
                logLevel = json.get("logLevel").getAsInt();
                forgeLogLevel = json.get("forgeLogLevel").getAsInt();
                showConsoleByLevel = json.get("showConsoleByLevel").getAsInt();
                maxRam = json.get("maxRam").getAsInt();
                maxPermGen = json.get("maxPermGen").getAsInt();
                size = gson.fromJson(json.get("size"), Dimension.class);
                location = gson.fromJson(json.get("location"), Point.class);
                maximisedMc = json.get("maximisedMc").getAsBoolean();
                javaArgs = json.get("javaArgs").getAsString();
                selectedPack = json.get("selectedPack").getAsInt();
                logger.info("User options loaded.");
            } catch (IOException e) {
                //logger.severe("Couldn't find the options file.<br> I'm going to create a blank one.");
            }
        }

    }

    */
/**
 * Method invokes the saving of data to the loption file.
 *//*

    public void saveProperties(boolean exit) {

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .create();
        JsonObject json = new JsonObject();
        json.add("version", gson.toJsonTree(version));
        json.addProperty("build", build);
        json.addProperty("showConsole", showConsole);
        json.addProperty("logLevel", logLevel);
        json.addProperty("forgeLogLevel", forgeLogLevel);
        json.addProperty("showConsoleByLevel", showConsoleByLevel);
        json.addProperty("maxRam", maxRam);
        json.addProperty("maxPermGen", maxPermGen);
        json.add("size", gson.toJsonTree(size));
        json.add("location", gson.toJsonTree(location));
        json.addProperty("maximisedMc", maximisedMc);
        json.addProperty("javaArgs", javaArgs);
        json.addProperty("selectedPack", selectedPack);

        // Process of serialization
        String text = gson.toJson(json);
        try {
            FileWriter writer = new FileWriter(options);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            //logger.severe("You are executing the launcher from a location without write permission.</br> Change this to save any options.");
        }
        logger.info("User options saved.");

        if (exit) {
            logger.info("<b>Exiting</b>");
            System.exit(0);
        }
    }

    public void loadData() {

    }

    */
/**
 * @return the home
 *//*

    public File getHome() {
        return home;
    }

    */
/**
 * @param home the home to set
 *//*

    public void setHome(File home) {
        this.home = home;
    }

    */
/**
 * @return the files
 *//*

    public File getFiles() {
        return files;
    }

    */
/**
 * @param files the files to set
 *//*

    public void setFiles(File files) {
        this.files = files;
    }

    */
/**
 * @return the instances
 *//*

    public File getInstances() {
        return instances;
    }

    */
/**
 * @param instances the instances to set
 *//*

    public void setInstances(File instances) {
        this.instances = instances;
    }

    */
/**
 * @return the data
 *//*

    public File getData() {
        return data;
    }

    */
/**
 * @param data the data to set
 *//*

    public void setData(File data) {
        this.data = data;
    }

    */
/**
 * @return the modRepo
 *//*

    public File getModRepo() {
        return modRepo;
    }

    */
/**
 * @param modRepo the modRepo to set
 *//*

    public void setModRepo(File modRepo) {
        this.modRepo = modRepo;
    }

    */
/**
 * @return the fileRepo
 *//*

    public File getFileRepo() {
        return fileRepo;
    }

    */
/**
 * @param fileRepo the fileRepo to set
 *//*

    public void setFileRepo(File fileRepo) {
        this.fileRepo = fileRepo;
    }

    */
/**
 * @return the assets
 *//*

    public File getAssets(File instance) {
        return new File(instance.getAbsolutePath() + assets + File.separator);
    }

    */
/**
 * @param assets the assets to set
 *//*

    public void setAssets(String assets) {
        this.assets = assets;
    }

    */
/**
 * @return the libraries
 *//*

    public File getLibraries(File instance) {
        return new File(instance.getAbsolutePath() + libraries + File.separator);
    }

    */
/**
 * @param libraries the libraries to set
 *//*

    public void setLibraries(String libraries) {
        this.libraries = libraries;
    }

    */
/**
 * @return the versions
 *//*

    public File getVersions(File instance) {
        return new File(instance.getAbsolutePath() + versions + File.separator);
    }

    */
/**
 * @param versions the versions to set
 *//*

    public void setVersions(String versions) {
        this.versions = versions;
    }

    */
/**
 * @return the resources
 *//*

    public File getResources(File instance) {
        return new File(instance.getAbsolutePath() + resources + File.separator);
    }

    */
/**
 * @param resources the resources to set
 *//*

    public void setResources(String resources) {
        this.resources = resources;
    }

    */
/**
 * @return the mods
 *//*

    public File getMods(File instance) {
        return new File(instance.getAbsolutePath() + mods + File.separator);
    }

    */
/**
 * @param mods the mods to set
 *//*

    public void setMods(String mods) {
        this.mods = mods;
    }

    */
/**
 * @return the configs
 *//*

    public File getConfigs(File instance) {
        return new File(instance.getAbsolutePath() + configs + File.separator);
    }

    */
/**
 * @param configs the configs to set
 *//*

    public void setConfigs(String configs) {
        this.configs = configs;
    }

    */
/**
 * @return the minecraftVersions
 *//*

    public File getMinecraftVersions() {
        return minecraftVersions;
    }

    */
/**
 * @param minecraftVersions the minecraftVersions to set
 *//*

    public void setMinecraftVersions(File minecraftVersions) {
        this.minecraftVersions = minecraftVersions;
    }

    */
/**
 * @return the forgeVersions
 *//*

    public File getForgeVersions() {
        return forgeVersions;
    }

    */
/**
 * @param forgeVersions the forgeVersions to set
 *//*

    public void setForgeVersions(File forgeVersions) {
        this.forgeVersions = forgeVersions;
    }

    */
/**
 * @return the packs
 *//*

    public File getPacks() {
        return packs;
    }

    */
/**
 * @param packs the packs to set
 *//*

    public void setPacks(File packs) {
        this.packs = packs;
    }

    */
/**
 * @return the options
 *//*

    public File getOptions() {
        return options;
    }

    */
/**
 * @param options the options to set
 *//*

    public void setOptions(File options) {
        this.options = options;
    }

    */
/**
 * @return the modList
 *//*

    public File getModList() {
        return modList;
    }

    */
/**
 * @param modList the modList to set
 *//*

    public void setModList(File modList) {
        this.modList = modList;
    }

    */
/**
 * @return the configList
 *//*

    public File getConfigList() {
        return configList;
    }

    */
/**
 * @param configList the configList to set
 *//*

    public void setConfigList(File configList) {
        this.configList = configList;
    }

    */
/**
 * @return the console
 *//*

    public Console getConsole() {
        return console;
    }

    */
/**
 * @param console the console to set
 *//*

    public void setConsole(Console console) {
        this.console = console;
    }

    */
/**
 * @return the serverLogin
 *//*

    public ftpData getServerLogin() {
        return serverLogin;
    }

    */
/**
 * @param serverLogin the serverLogin to set
 *//*

    public void setServerLogin(ftpData serverLogin) {
        this.serverLogin = serverLogin;
    }

    */
/**
 * @return the vJson
 *//*

    public String getvJson() {
        return vJson;
    }

    */
/**
 * @param vJson the vJson to set
 *//*

    public void setvJson(String vJson) {
        this.vJson = vJson;
    }

    */
/**
 * @return the assJson
 *//*

    public String getAssJson() {
        return assJson;
    }

    */
/**
 * @param assJson the assJson to set
 *//*

    public void setAssJson(String assJson) {
        this.assJson = assJson;
    }

    */
/**
 * @return the ftpJson
 *//*

    public String getFtpJson() {
        return ftpJson;
    }

    */
/**
 * @param ftpJson the ftpJson to set
 *//*

    public void setFtpJson(String ftpJson) {
        this.ftpJson = ftpJson;
    }

    */
/**
 * @return the logFile
 *//*

    public String getLogFile() {
        return logFile;
    }

    */
/**
 * @param logFile the logFile to set
 *//*

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    */
/**
 * @return the temp
 *//*

    public File getTemp() {
        return temp;
    }

    */
/**
 * @param temp the temp to set
 *//*

    public void setTemp(File temp) {
        this.temp = temp;
    }

    */
/**
 * @return the mcVersions
 *//*

    public List<File> getMcVersions() {
        return mcVersions;
    }

    */
/**
 * @param mcVersions the mcVersions to set
 *//*

    public void setMcVersions(List<File> mcVersions) {
        this.mcVersions = mcVersions;
    }

    */
/**
 * @return the fgVersions
 *//*

    public List<File> getFgVersions() {
        return fgVersions;
    }

    */
/**
 * @param fgVersions the fgVersions to set
 *//*

    public void setFgVersions(List<File> fgVersions) {
        this.fgVersions = fgVersions;
    }

    */
/**
 * @return the legacyConfigs
 *//*

    public File getLegacyConfigs() {
        return legacyConfigs;
    }

    */
/**
 * @param legacyConfigs the legacyConfigs to set
 *//*

    public void setLegacyConfigs(File legacyConfigs) {
        this.legacyConfigs = legacyConfigs;
    }

    */
/**
 * @return the legacyMods
 *//*

    public File getLegacyMods() {
        return legacyMods;
    }

    */
/**
 * @param legacyMods the legacyMods to set
 *//*

    public void setLegacyMods(File legacyMods) {
        this.legacyMods = legacyMods;
    }

    */
/**
 * @return the instanceList
 *//*

    public List<File> getInstanceList() {
        instanceList = Arrays.asList(packs.listFiles());
        return instanceList;
    }

    */
/**
 * @return the gDev
 *//*

    public GraphicsDevice[] getGDev() {
        return GDev;
    }

    */
/**
 * @return the location
 *//*

    public Point getLocation() {
        return location;
    }

    */
/**
 * @param location the location to set
 *//*

    public void setLocation(Point location) {
        this.location = location;
    }

    */
/**
 * @return the build
 *//*

    public Build getBuild() {
        return Build.getType(build);
    }

    */
/**
 * @param build the build to set
 *//*

    public void setBuild(Build build) {
        this.build = build.getValue();
    }

    */
/**
 * @return the showConsole
 *//*

    public boolean isShowConsole() {
        return showConsole;
    }

    */
/**
 * @param showConsole the showConsole to set
 *//*

    public void setShowConsole(boolean showConsole) {
        this.showConsole = showConsole;
    }

    */
/**
 * @return the logLevel
 *//*

    public int getLogLevel() {
        return logLevel;
    }

    */
/**
 * @param logLevel the logLevel to set
 *//*

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    */
/**
 * @return the forgeLogLeve
 *//*

    public int getForgeLogLeve() {
        return forgeLogLevel;
    }

    */
/**
 * @param forgeLogLeve the forgeLogLeve to set
 *//*

    public void setForgeLogLeve(int forgeLogLeve) {
        this.forgeLogLevel = forgeLogLeve;
    }

    */
/**
 * @return the showConsoleByLevel
 *//*

    public int getShowConsoleByLevel() {
        return showConsoleByLevel;
    }

    */
/**
 * @param showConsoleByLevel the showConsoleByLevel to set
 *//*

    public void setShowConsoleByLevel(int showConsoleByLevel) {
        this.showConsoleByLevel = showConsoleByLevel;
    }

    */
/**
 * @return the maxRam
 *//*

    public int getMaxRam() {
        return maxRam;
    }

    */
/**
 * @param maxRam the maxRam to set
 *//*

    public void setMaxRam(int maxRam) {
        this.maxRam = maxRam;
    }

    */
/**
 * @return the maxPermGen
 *//*

    public int getMaxPermGen() {
        return maxPermGen;
    }

    */
/**
 * @param maxPermGen the maxPermGen to set
 *//*

    public void setMaxPermGen(int maxPermGen) {
        this.maxPermGen = maxPermGen;
    }

    */
/**
 * @return the size
 *//*

    public Dimension getSize() {
        return size;
    }

    */
/**
 * @param size the size to set
 *//*

    public void setSize(Dimension size) {
        this.size = size;
    }

    */
/**
 * @return the gDevice
 *//*

    public int getGDevice() {
        return GDevice;
    }

    */
/**
 * @param gDevice the gDevice to set
 *//*

    public void setGDevice(int gDevice) {
        GDevice = gDevice;
    }

    */
/**
 * @return the maximisedMc
 *//*

    public boolean isMaximisedMc() {
        return maximisedMc;
    }

    */
/**
 * @param maximisedMc the maximisedMc to set
 *//*

    public void setMaximisedMc(boolean maximisedMc) {
        this.maximisedMc = maximisedMc;
    }

    */
/**
 * @return the javaArgs
 *//*

    public String getJavaArgs() {
        return javaArgs;
    }

    */
/**
 * @param javaArgs the javaArgs to set
 *//*

    public void setJavaArgs(String javaArgs) {
        this.javaArgs = javaArgs;
    }

    */
/**
 * @return the selectedPack
 *//*

    public int getSelectedPack() {
        return selectedPack;
    }

    */
/**
 * @param selectedPack the selectedPack to set
 *//*

    public void setSelectedPack(int selectedPack) {
        this.selectedPack = selectedPack;
    }

    */
/**
 * @param version the version to set
 *//*

    public void setVersion(int[] version) {
        this.version = version;
    }

    public int[] getVersion() {
        return version;
    }

    public File getPackInfo(int ID) {
        return new File(packs.getAbsolutePath() + File.separator + getInstanceList().get(ID).getName() + ".info");
    }

    */
/**
 * @return the disponiblePacks
 *//*

    public File getDisponiblePacks() {
        return disponiblePacks;
    }

    */
/**
 * @param disponiblePacks the disponiblePacks to set
 *//*

    public void setDisponiblePacks(File disponiblePacks) {
        this.disponiblePacks = disponiblePacks;
    }

    */
/**
 * @return the packList
 *//*

    public List<Object> getPackList() {
        return packList;
    }

    */
/**
 * @param packList the packList to set
 *//*

    public void setPackList(List<Object> packList) {
        this.packList = packList;
    }

    public void addPack(Pack pack) {
        packList.add(pack);
    }

}
*/
