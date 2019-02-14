package com.levelb.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Администратор on 14.02.2019.
 */
public class MessageBox {

    private MainOffice mainOffice;
    private List<Message> messages;
    private static long nextIndex = 0;

    public MessageBox() {
        this(100);
    }

    public MessageBox(int count) {
        messages = new ArrayList<>(count);
        mainOffice = new MainOffice();
    }

    public long add(Message.MessageCategory category, String sender, String address, String receiver) {
        Message message = new Message(category, sender, address, receiver);
        message.setId(nextIndex++);
        messages.add(message);
        return message.getId();
    }

    public Message search(long id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    public boolean delete(long id) {
        Message message = search(id);
        return messages.remove(message);
    }

    public List<Long> sendToMainOffice() {
        List<Message> toRemove = new ArrayList<>();
        for (Message message : messages) {
            boolean accepted = mainOffice.queue(message);
            if (accepted) {
                toRemove.add(message);
            }
        }
        List<Long> ids = new ArrayList<>();
        for (Message message : toRemove) {
            ids.add(message.getId());
            messages.remove(message);
        }
        return ids;
    }

    public List<Message> list() {
        return new ArrayList<>(messages);
    }

    @Override
    public String toString() {
        return "MessageBox{" +
                "messages=" + messages +
                '}';
    }
}
