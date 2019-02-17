package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 15.02.2019.
 */
public class ExitCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        printer.println("Exiting...");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "ExitCommand{}";
    }
}
