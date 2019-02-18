package com.levelb.post.command;

import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 15.02.2019.
 */
public class HelpCommand implements UserCommand {
    @Override
    public void execute(PostBusinessLogic logic, OutputPrinter printer) {
        printer.println("You can use following commands: ");
        printer.printHelp();
    }

    @Override
    public String toString() {
        return "HelpCommand{}";
    }
}
