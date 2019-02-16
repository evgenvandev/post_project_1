package com.levelb.post.command;

import com.levelb.post.Message;

/**
 * Created by Администратор on 15.02.2019.
 */
public class AddCommand implements UserCommand {
    private String sender;
    private String receiver;
    private String address;
    private Message.MessageCategory category;

    public AddCommand(String sender, String receiver, String address, Message.MessageCategory category) {
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.category = category;
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
}
