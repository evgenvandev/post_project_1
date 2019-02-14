package com.levelb.post;

import java.util.Arrays;

/**
 * Created by Администратор on 14.02.2019.
 */
public class MessageBox {

    private Message[] messages;
    private int current;
    private static long nextIndex = 0;

    public MessageBox(int count) {
        messages = new Message[count];
        current = 0;
    }

    public long add(Message.MessageCategory category, String sender, String address, String receiver) {
        Message message = new Message(category, sender, address, receiver);
        message.setId(nextIndex++);
        messages[current] = message;
        current++;
        return message.getId();
    }

    @Override
    public String toString() {
        return "MessageBox{" +
                "messages=" + Arrays.toString(messages) +
                '}';
    }
}
