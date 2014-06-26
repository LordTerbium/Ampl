package main.Service.Authentication;

import main.Interface.Log.LogWrapper;
import main.Service.Settings.Player.User;

import javax.swing.*;

import static main.Ampl.getSettings;

/**
 * The class provides a set of methods to login, refresh and logout a user. Created by Max on 22.06.2014.
 */
public class Authentication {

    private LogWrapper logger = LogWrapper.getLogger(Authentication.class);

    public boolean login(String username, char[] password) {

        LogIn task = new LogIn(username, password);
        boolean successful = false;
        int rCode;

        rCode = task.login();
        if (rCode == 200) {
            User newUser = getSettings().getAccounts().addUser(task.getResponse().getSelectedProfile().getName());

            newUser.setAccesToken(task.getResponse().getAccesToken());
            newUser.setId(task.getResponse().getSelectedProfile().getId());
            newUser.setLegacy(task.getResponse().getSelectedProfile().getLegacy());

            successful = true;
        } else {
            logger.fatal("Login failed. Correct the mistakes and try again.");
            logger.debug("Errorcode: " + task.getErrorResponse().getErrorCode());
            JOptionPane.showMessageDialog(null, task.getErrorResponse().getErrorReport(), "Log-in failed",
                    JOptionPane.ERROR_MESSAGE);
        }

        return successful;
    }
}
