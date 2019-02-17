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
        log.debug(String.format("Create message box with initial size = %d", count));
        messages = new ArrayList<>(count);
        mainOffice = new MainOffice();
        List<Message> existingMessages = read();
        messages.addAll(existingMessages);

        for (Message existingMessage : existingMessages) {
            nextIndex = Math.max(nextIndex, existingMessage.getId()) + 1;
        }

//        nextIndex = existingMessages.stream().map(Message::getId).max(Long::compare).orElseGet(() -> -1L) +1;
    }

    public long add(Message.MessageCategory category, String sender, String address, String receiver) {
        log.debug(String.format("Add following message: category = %s, from %s, to %s(address: %s);", category, sender, receiver, address));
        Message message = new Message(category, sender, address, receiver);
        message.setId(nextIndex++);
        messages.add(message);
        save();
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
                save();
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
        save();
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

    public void save() {
        try(PrintWriter writer = new PrintWriter(Paths.get("messages.txt").toFile())){
            for (Message message : messages) {
                writer.print(message.getId());
                writer.print("\t");
                writer.print(message.getCategory());
                writer.print("\t");
                writer.print(message.getSender());
                writer.print("\t");
                writer.print(message.getReceiver());
                writer.print("\t");
                writer.print(message.getAddress());
                writer.print("\t");
                writer.print(message.getDate().getTime());
                writer.println();
            }
        } catch (FileNotFoundException e) {
            log.error("Error writing file:", e);
        }
    }

    public List<Message> read() {
        List<Message> messages = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("messages.txt"));
            for (String line : lines) {
                String[] tokens = line.split("\t");
                Long id = Long.parseLong(tokens[0]);
                Message.MessageCategory category = Message.MessageCategory.valueOf(tokens[1]);
                Date date = new Date(Long.parseLong(tokens[5]));
                messages.add(new Message(id, category, tokens[2], tokens[4], tokens[3], date));
            }
        } catch (IOException e) {
            log.error("Error reading file:", e);
        }
        return messages;
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

        save();
    }
}
