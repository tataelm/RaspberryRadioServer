package com.raspradio;

import com.raspradio.dbobjects.Channels;
import com.raspradio.dbobjects.Commands;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Main {

    public static List<Channels> LIST_CHANNELS = new ArrayList<>();
    public static Commands COMMANDS = new Commands();

    public static Runtime RUNTIME = Runtime.getRuntime();
    public static Process PROCESS = null;

    public static void main(String[] args) throws SQLException, NamingException {

        RadioAdministrator radioAdministrator = new RadioAdministrator();
        radioAdministrator.setVariablesToDefault();
        radioAdministrator.fillStaticVariables();

        String channelUrl = COMMANDS.getChannels().getURL();
        try {
            String[] command = { "omxplayer", channelUrl};
            PROCESS = RUNTIME.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new RunningService(), 0, 500);
    }


}
