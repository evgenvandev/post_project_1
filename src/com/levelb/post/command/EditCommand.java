package com.levelb.post.command;

import com.levelb.post.Message;

/**
 * Created by Администратор on 15.02.2019.
 */
public class EditCommand implements UserCommand {
    private long id;
    private String sender;
    private String receiver;
    private String address;
    private Message.MessageCategory category;

    public EditCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public Message.MessageCategory getCategory() {
        return category;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(Message.MessageCategory category) {
        this.category = category;
    }
}
