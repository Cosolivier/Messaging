package com.coso.channelmessaging;

/**
 * Created by coso on 30/01/2017.
 */
public class Message {




    int userID;
    String message;
    String date;
    String image;

    public Message(int userID , String message , String date, String image){

        this.userID=userID;
        this.message=message;
        this.date=date;
        this.image=image;

    }

    @Override
    public String toString() {

            return "Message{" +
                    "userID=" + userID +
                    ", message='" + message + '\'' +
                    ", date='" + date + '\'' +
                    ", image='" + image + '\'' +
                    '}';
    }

    public int getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
