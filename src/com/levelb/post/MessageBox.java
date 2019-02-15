package com.levelb.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        Iterator<Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message next = iterator.next();
            if (next.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Long> sendToMainOffice() {
        List<Long> ids = new ArrayList<>();
        Iterator<Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message next = iterator.next();
            boolean accepted = mainOffice.queue(next);
            if (accepted) {
                ids.add(next.getId());
                iterator.remove();
            }
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
