package com.raspradio;

import com.raspradio.dbobjects.Channels;
import com.raspradio.dbobjects.Commands;
import com.raspradio.Player.OMX;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.TimerTask;

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

            try {
                OutputStream cmd_out = Main.PROCESS.getOutputStream();
                cmd_out.write(OMX.PAUSE_RESUME);
                cmd_out.flush();
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
                OutputStream cmd_out = Main.PROCESS.getOutputStream();
                cmd_out.write(OMX.VOLUME_DOWN);
                cmd_out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (Main.COMMANDS.isVolumeUp() != changingCommands.isVolumeUp())
        {
            System.out.println("volume up");
            Main.COMMANDS.setVolumeUp(false);

            ra.setVolumeUp();

            try {
                OutputStream cmd_out = Main.PROCESS.getOutputStream();
                cmd_out.write(OMX.VOLUME_UP);
                cmd_out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (Main.COMMANDS.getChannels().getID() != changingCommands.getChannels().getID())
        {
            System.out.println("Channel changed");
            Main.COMMANDS.setChannels(changingCommands.getChannels());

            String channelUrl = changingCommands.getChannels().getURL();

            try {
                /*
                String[] command1 = {"killall", "omxplayer.bin"};
                Main.PROCESS = Main.RUNTIME.exec(command1);
                */
                OutputStream cmd_out = Main.PROCESS.getOutputStream();
                cmd_out.write(OMX.EXIT);
                cmd_out.flush();
                String[] command = { "omxplayer", channelUrl};
                Main.PROCESS = Main.RUNTIME.exec(command);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
