package com.atahan.dbobjects;


public class Commands {

    boolean onOff;
    Channels channels;
    boolean volumeUp;
    boolean volumeDown;
    boolean mute;

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public Channels getChannels() {
        return channels;
    }

    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    public boolean isVolumeUp() {
        return volumeUp;
    }

    public void setVolumeUp(boolean volumeUp) {
        this.volumeUp = volumeUp;
    }

    public boolean isVolumeDown() {
        return volumeDown;
    }

    public void setVolumeDown(boolean volumeDown) {
        this.volumeDown = volumeDown;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }



}
