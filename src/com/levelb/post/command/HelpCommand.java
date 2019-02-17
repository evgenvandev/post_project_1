package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 15.02.2019.
 */
public class HelpCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        printer.println("You can use following commands: ");
        printer.printSeparator();
        printer.println("add");
        printer.println("send");
        printer.println("list");
        printer.println("edit <<id>>");
        printer.println("remove <<id>>");
        printer.println("help");
        printer.println("exit");
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "HelpCommand{}";
    }
}
