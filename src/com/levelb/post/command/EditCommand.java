package com.levelb.post.command;

import com.levelb.post.Message;
import com.levelb.post.MessageBox;

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

    @Override
    public void execute(MessageBox messageBox) {
        Message message = messageBox.search(id);
        if (message == null) {
            System.out.println("Couldn't find message " + id + "!");
            return;
        }
        message.setCategory(category);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setAddress(address);
    }
}
