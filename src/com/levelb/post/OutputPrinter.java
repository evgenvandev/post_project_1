package com.levelb.post;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

/**
 * Created by Администратор on 16.02.2019.
 */
public class OutputPrinter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private PrintStream outStream;

    public OutputPrinter(PrintStream outStream) {
        this.outStream = outStream;
    }

    public void printSeparator() {
        outStream.println("-----------------------------------------");
        outStream.println();
    }

    public void println(String s) {
        outStream.println(s);
    }

    public void print(String s) {
        outStream.print(s);
    }

    public void printf(String format, Object... args) {
        outStream.printf(format, args);
    }

    public void printUserPrompt() {
        outStream.print(">> ");
    }

    public void print(Message message) {
        printAlign("Message", message.getId());
        printAlign("Category", message.getCategory());
        printAlign("From", message.getSender());
        printAlign("To", message.getReceiver());
        printAlign("Address", message.getAddress());
        printAlign("Date", dateFormat.format(message.getDate()));
    }

    public void printAlign(String caption, String data) {
        outStream.printf("%10s: %s%n", caption, data);
    }

    public void printAlign(String caption, Object data) {
        printAlign(caption, data.toString());
    }

    public void println() {
        printf("%n");
    }

    public void printSmallSeparator() {
        outStream.println("----------");
    }

    public void printHelp(UserCommandLineCommand command) {
        String fullName = command.getName();
        for (UserCommandLineCommand.Parameter parameter : command.getParameters()) {
            fullName += " [" + parameter.getName() + "]";
        }
        outStream.printf("%15s - %s%n", fullName, command.getDescription());
        for (UserCommandLineCommand.Parameter parameter : command.getParameters()) {
            outStream.printf("%20s: %s%n", parameter.getName(), parameter.getDescription());
        }
    }

    public void printHelp() {
        printSeparator();
        printHelp(new UserCommandLineCommand("add", "Adds new message to message box."));
        printHelp(new UserCommandLineCommand("edit", "Edit specified message.",
                new UserCommandLineCommand.Parameter("id","message identifier.")));
        printHelp(new UserCommandLineCommand("remove", "Removes specified message.",
                new UserCommandLineCommand.Parameter("id","message identifier.")));
        printHelp(new UserCommandLineCommand("send", "Sends all messages to the main office."));
        printHelp(new UserCommandLineCommand("list", "Shows all messages from message box."));
        printHelp(new UserCommandLineCommand("help", "Shows this help."));
        printHelp(new UserCommandLineCommand("exit", "Exit application."));
        println();
        printSeparator();
    }
}
