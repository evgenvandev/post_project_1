package com.levelb.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Администратор on 14.02.2019.
 */
public class MessageBox {
    private static final Logger log = LoggerFactory.getLogger(MessageBox.class);

    private MainOffice mainOffice;
    private List<Message> messages;
    private static long nextIndex = 0;

    public MessageBox() {
        this(100);
    }

    public MessageBox(int count) {
        this(count, new ArrayList<>());
    }

    public MessageBox(int count, List<Message> messages) {
        log.debug(String.format("Create message box with initial size = %d", count));
        this.messages = new ArrayList<>(count);
        mainOffice = new MainOffice();
        this.messages.addAll(messages);

        for (Message existingMessage : messages) {
            nextIndex = Math.max(nextIndex, existingMessage.getId()) + 1;
        }

//        nextIndex = existingMessages.stream().map(Message::getId).max(Long::compare).orElseGet(() -> -1L) +1;
    }

    public long add(Message.MessageCategory category, String sender, String address, String receiver) {
        log.debug(String.format("Add following message: category = %s, from %s, to %s(address: %s);", category, sender, receiver, address));
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
                log.debug(String.format("Delete message: %d", id));
                return true;
            }
        }
        log.debug(String.format("Couldn't delete message: %d", id));
        return false;
    }

    public List<Long> sendToMainOffice() {
        log.debug("Sending messages to main office.");
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

    public void update(long id, Message.MessageCategory category, String sender, String receiver, String address) {
        Message message = search(id);
        if (message == null) {
            return;
        }
        message.setCategory(category);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setAddress(address);
    }
}
