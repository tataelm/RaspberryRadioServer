package com.atahan;

import com.atahan.dbobjects.Channels;
import com.atahan.dbobjects.Commands;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import static java.lang.Runtime.getRuntime;

public class RunningService extends TimerTask {



    public void run() {

        RadioAdministrator ra = new RadioAdministrator();
        List<Channels> changingListChannels = ra.getChannelObjects();
        Commands changingCommands = ra.getCommandsObjects(changingListChannels);

        if (Main.COMMANDS.isOnOff() != changingCommands.isOnOff())
        {
           /* System.out.println("turned on/off");
            Main.COMMANDS.setOnOff(changingCommands.isOnOff());
            // TODO kapatma - -client a çevir    --play-pause
           // String channelUrl = changingCommands.getChannels().getURL();
            String[] command = null;

            if (changingCommands.isOnOff())
            {
              //  command = new String[]{"xterm", "-e", "rhythmbox-client", "--play-pause"};
            }

          /*  try {
               // String[] command = { "xterm", "-e", "rhythmbox-client", channelUrl };
               // getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        if (Main.COMMANDS.isMute() != changingCommands.isMute())
        {
            System.out.println("mute clicked");
            Main.COMMANDS.setMute(changingCommands.isMute());

            String[] command = null;

            if (changingCommands.isMute())
            {
                command = new String[]{"xterm", "-e", "rhythmbox-client", "--set-volume", "0"};
            }
            else
            {
                command = new String[]{"xterm", "-e", "rhythmbox-client", "--set-volume", "0.5"};
            }

            try {
                getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (Main.COMMANDS.isVolumeDown() != changingCommands.isVolumeDown())
        {
            System.out.println("volume down");
            Main.COMMANDS.setVolumeDown(false);

            ra.setVolumeDown();

            try {
                String[] command = { "xterm", "-e", "rhythmbox-client", "--volume-down" };
                getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (Main.COMMANDS.isVolumeUp() != changingCommands.isVolumeUp())
        {
            System.out.println("volume up");
            Main.COMMANDS.setVolumeUp(false);

            ra.setVolumeUp();

            try {
                String[] command = { "xterm", "-e", "rhythmbox-client", "--volume-up" };
                getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Main.COMMANDS.getChannels().getID() != changingCommands.getChannels().getID())
        {
            System.out.println("Channel changed");
            Main.COMMANDS.setChannels(changingCommands.getChannels());

            String channelUrl = changingCommands.getChannels().getURL();
            try {
                String[] command = { "xterm", "-e", "rhythmbox", channelUrl };
                getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
