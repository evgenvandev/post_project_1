package com.levelb.post;

import java.util.List;

/**
 * Created by Администратор on 17.02.2019.
 */
public class PostBusinessLogic {

    private MessageBox messageBox;
    private FileDataStorage storage;

    public PostBusinessLogic() {
        storage = new FileDataStorage("messages.txt");
        List<Message> messages = storage.read();
        messageBox = new MessageBox(100, messages);
    }

    public long add(Message.MessageCategory category, String sender, String address, String receiver) {
        long id = messageBox.add(category, sender, address, receiver);
        storage.save(messageBox.list());
        return id;
    }

    public boolean delete(long id) {
        boolean delete = messageBox.delete(id);
        storage.save(messageBox.list());
        return delete;
    }

    public List<Long> sendToMainOffice() {
        List<Long> sent = messageBox.sendToMainOffice();
        storage.save(messageBox.list());
        return sent;
    }

    public void update(long id, Message.MessageCategory category, String sender, String receiver, String address) {
        messageBox.update(id, category, sender, receiver, address);
        storage.save(messageBox.list());
    }

    public Message search(long id) {
        return messageBox.search(id);
    }

    public List<Message> list() {
        return messageBox.list();
    }
}
