package com.levelb.post.command;

import com.levelb.post.Message;
import com.levelb.post.MessageBox;

/**
 * Created by Администратор on 15.02.2019.
 */
public class ListCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox) {
        System.out.println("List of messages: ");
        for (Message message : messageBox.list()) {
            System.out.println(message);
        }
    }
}
