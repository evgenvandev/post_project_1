package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;
import com.levelb.post.UserCommandLineCommand;

/**
 * Created by Администратор on 15.02.2019.
 */
public class HelpCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        printer.println("You can use following commands: ");
        printer.printHelp();
    }

    @Override
    public String toString() {
        return "HelpCommand{}";
    }
}
