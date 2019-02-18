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

        PostBusinessLogic logic = new PostBusinessLogic();
        OutputPrinter printer = new OutputPrinter(System.out);
        UseConsoleInputReader consoleInputReader = new UseConsoleInputReader(printer);
        printer.println("Please use following commands:");
        printer.printHelp();
        while (true) {
            printer.printUserPrompt();
            UserCommand userCommand = consoleInputReader.nextCommand();
            if (userCommand == null) {
                printer.println("Unknown command: ");
            } else {
                commandLog.debug(userCommand.toString());
                execute(userCommand, logic, consoleInputReader, printer);
            }
        }
    }

    private static void execute(UserCommand userCommand, PostBusinessLogic logic, UseConsoleInputReader consoleInputReader, OutputPrinter printer) {
        if (userCommand instanceof EditCommand) {
            EditCommand command = (EditCommand) userCommand;
            Message message = logic.search(command.getId());
            if (message == null) {
                printer.println("User unknown!");
            } else {
                consoleInputReader.readEdit(command, message.getSender(), message.getReceiver(), message.getAddress(), message.getCategory());
            }
        }
        userCommand.execute(logic, printer);
    }
}
