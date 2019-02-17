package com.levelb.post.command;

import com.levelb.post.Message;
import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 15.02.2019.
 */
public class ListCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        printer.println("List of messages: ");
        for (Message message : messageBox.list()) {
            printer.println(message + "");
        }
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "ListCommand{}";
    }
}
