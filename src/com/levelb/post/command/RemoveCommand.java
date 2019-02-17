package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 15.02.2019.
 */
public class RemoveCommand implements UserCommand {
    private long id;

    public RemoveCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        boolean delete = messageBox.delete(id);
        if (delete) {
            printer.println("Message " + id + " was successfully removed!");
        } else {
            printer.println("Message " + id + " was not found!");
        }
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id=" + id +
                '}';
    }
}
