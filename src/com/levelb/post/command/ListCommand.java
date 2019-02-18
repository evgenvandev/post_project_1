package com.levelb.post.command;

import com.levelb.post.Message;
import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 15.02.2019.
 */
public class ListCommand implements UserCommand {
    @Override
    public void execute(PostBusinessLogic logic, OutputPrinter printer) {
        printer.println();
        printer.println("List of messages: ");
        for (Message message : logic.list()) {
            printer.printSmallSeparator();
            printer.print(message);
            printer.println();
        }
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "ListCommand{}";
    }
}
