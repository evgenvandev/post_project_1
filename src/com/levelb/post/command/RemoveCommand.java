package com.levelb.post.command;

import com.levelb.post.MessageBox;

/**
 * Created by Администратор on 15.02.2019.
 */
public class RemoveCommand implements UserCommand {
    private long id;

    public RemoveCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(MessageBox messageBox) {
        boolean delete = messageBox.delete(id);
        if (delete) {
            System.out.println("Message " + id + " was successfully removed!");
        } else {
            System.out.println("Message " + id + " was not found!");
        }
    }
}
