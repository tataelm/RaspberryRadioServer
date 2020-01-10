package com.atahan;

import com.atahan.dbobjects.Channels;
import com.atahan.dbobjects.Commands;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RadioAdministrator {

    MysqlConnect _sql = new MysqlConnect();

    public void fillStaticVariables()
    {
        Main.LIST_CHANNELS = getChannelObjects();
        Main.COMMANDS = getCommandsObjects(Main.LIST_CHANNELS);
    }

    public List<Channels> getChannelObjects() {
        String sql = "SELECT * FROM channels";

        List<Channels> tmpListChannel = new ArrayList<>();

        try {
            PreparedStatement statement = _sql.connect().prepareStatement(sql);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Channels channelObj = new Channels();
                channelObj.setID(resultSet.getInt("id"));
                channelObj.setName(resultSet.getString("name"));
                channelObj.setURL(resultSet.getString("url"));
                channelObj.setFavourite(resultSet.getBoolean("isFavourite"));

                tmpListChannel.add(channelObj);
            }

            return tmpListChannel;
        } catch (SQLException e) {
            e.printStackTrace();
            return tmpListChannel;
        } finally {
            _sql.disconnect();
        }
    }

    public Commands getCommandsObjects(List<Channels> tmpListChannels) {
        Commands tmpCommands = new Commands();

        String sql = "SELECT * FROM commands";
        try {
            PreparedStatement statement = _sql.connect().prepareStatement(sql);
            var resultSet = statement.executeQuery();

            while (resultSet.next()) {

                tmpCommands.setOnOff(resultSet.getBoolean("on_off"));
                tmpCommands.setVolumeUp(resultSet.getBoolean("volume_up"));
                tmpCommands.setVolumeDown(resultSet.getBoolean("volume_down"));
                tmpCommands.setMute(resultSet.getBoolean("mute"));

                int channelId = resultSet.getInt("channel_id");

                Channels channelObj = tmpListChannels.stream()
                        .filter(u -> u.getID() == channelId)
                        .findAny()
                        .orElse(null);

                tmpCommands.setChannels(channelObj);
            }
            return tmpCommands;
        } catch (SQLException e) {
            e.printStackTrace();
            return tmpCommands;
        } finally {
            _sql.disconnect();
        }
    }


    public void setVolumeDown() {
        String sql = "UPDATE commands SET volume_down = 0";

        try {
            PreparedStatement statement = _sql.connect().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _sql.disconnect();
        }
    }

    public void setVariablesToDefault() {
        String sql = "UPDATE commands SET volume_down = 0, volume_up = 0, mute = 0, on_off = 1";

        try {
            PreparedStatement statement =  _sql.connect().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _sql.disconnect();
        }
    }

    public void setVolumeUp() {
        String sql = "UPDATE commands SET volume_up = 0";

        try {
            PreparedStatement statement = _sql.connect().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _sql.disconnect();
        }
    }
}
