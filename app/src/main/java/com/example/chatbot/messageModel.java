package com.example.chatbot;

public class messageModel
{
    static String sent_By_Me ="me";
    static String sent_By_Bot ="bot";
    String message,sentBy ;
    public  messageModel(String message,String sentBy){
        this.message=message;
        this.sentBy=sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }
}
