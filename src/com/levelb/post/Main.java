package com.levelb.post;

import com.levelb.post.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Администратор on 14.02.2019.
 */
public class Main {

    private static final Logger commandLog = LoggerFactory.getLogger("CommandLog");

    public static void main(String[] args) {
        //log.debug("Hello, I am logger!");

        MessageBox messageBox = new MessageBox();
        OutputPrinter printer = new OutputPrinter(System.out);
        UseConsoleInputReader consoleInputReader = new UseConsoleInputReader(printer);
        while (true) {
            printer.printUserPrompt();
            UserCommand userCommand = consoleInputReader.nextCommand();
            if (userCommand == null) {
                printer.println("Unknown command: ");
            } else {
                commandLog.debug(userCommand.toString());
                execute(userCommand, messageBox, consoleInputReader, printer);
            }
        }
    }

    private static void execute(UserCommand userCommand, MessageBox messageBox, UseConsoleInputReader consoleInputReader, OutputPrinter printer) {
        if (userCommand instanceof EditCommand) {
            EditCommand command = (EditCommand) userCommand;
            Message message = messageBox.search(command.getId());
            if (message == null) {
                printer.println("User unknown!");
            } else {
                consoleInputReader.readEdit(command, message.getSender(), message.getReceiver(), message.getAddress(), message.getCategory());
            }
        }
        userCommand.execute(messageBox, printer);
    }
}
