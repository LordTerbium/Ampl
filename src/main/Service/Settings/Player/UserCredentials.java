package main.Service.Settings.Player;

import main.Interface.Log.LogWrapper;

/**
 * The class contains temporarily data, like the password. This content won't be stored. Created by Max on 20.06.2014.
 */
public class UserCredentials {

    private LogWrapper logger = LogWrapper.getLogger(UserCredentials.class);
    private String username = "";
    private String password = "";

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        String pw = password;
        password = "";
        return pw;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
