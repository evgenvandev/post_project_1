package com.levelb.post.command;

import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 15.02.2019.
 */
public class RemoveCommand implements UserCommand {
    private long id;

    public RemoveCommand(long id) {
        this.id = id;
    }

    @Override
    public void execute(PostBusinessLogic logic, OutputPrinter printer) {
        boolean delete = logic.delete(id);
        printer.println();
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
