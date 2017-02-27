package com.coso.channelmessaging;

/**
 * Created by coso on 30/01/2017.
 */
public class Channel {

    int channelID;
    String name;
    String connectedusers;

    public Channel(int channelID , String name , String connectedusers){

        this.channelID=channelID;
        this.name=name;
        this.connectedusers=connectedusers;

    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getConnectedusers() {
        return connectedusers;
    }

    public void setConnectedusers(String connectedusers) {
        this.connectedusers = connectedusers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
