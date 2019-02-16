package com.levelb.post;

import com.levelb.post.command.*;

import java.util.List;

/**
 * Created by Администратор on 14.02.2019.
 */
public class Main {

    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox();
        UseConsoleInputReader consoleInputReader = new UseConsoleInputReader();
        while (true) {
            UserCommand userCommand = consoleInputReader.nextCommand();
            if (userCommand == null) {
                System.out.println("Unknown command: ");
            } else {
                execute(userCommand, messageBox, consoleInputReader);
            }
        }
    }

    private static void execute(UserCommand userCommand, MessageBox messageBox, UseConsoleInputReader consoleInputReader) {
        if (userCommand instanceof EditCommand) {
            EditCommand command = (EditCommand) userCommand;
            Message message = messageBox.search(command.getId());
            if (message == null) {
                System.out.println("User unknown!");
            } else {
                consoleInputReader.readEdit(command, message);
            }
            executedEdit(command, messageBox, message);
        }
        if (userCommand instanceof AddCommand) {
            executeAdd((AddCommand)userCommand, messageBox);
        }
        if (userCommand instanceof RemoveCommand) {
            executeRemove((RemoveCommand) userCommand, messageBox);
        }
        if (userCommand instanceof ExitCommand) {
            executeExit((ExitCommand) userCommand, messageBox);
        }
        if (userCommand instanceof HelpCommand) {
            executeHelp((HelpCommand) userCommand, messageBox);
        }
        if (userCommand instanceof SendCommand) {
            executeSend((SendCommand) userCommand, messageBox);
        }
        if (userCommand instanceof ListCommand) {
            executeList((ListCommand) userCommand, messageBox);
        }
    }

    private static void executeList(ListCommand userCommand, MessageBox messageBox) {
        System.out.println("List of messages: ");
        for (Message message : messageBox.list()) {
            System.out.println(message);
        }
    }

    private static void executeSend(SendCommand userCommand, MessageBox messageBox) {
        List<Long> longs = messageBox.sendToMainOffice();
        System.out.println("Following message were sent: " + longs);
    }

    private static void executeHelp(HelpCommand userCommand, MessageBox messageBox) {
        System.out.println("You can use following commands: ");
        System.out.println("add");
        System.out.println("send");
        System.out.println("list");
        System.out.println("edit <<id>>");
        System.out.println("remove <<id>>");
        System.out.println("help");
        System.out.println("exit");
    }

    private static void executeExit(ExitCommand userCommand, MessageBox messageBox) {
        System.out.println("Exiting...");
        System.exit(0);
    }

    private static void executeRemove(RemoveCommand userCommand, MessageBox messageBox) {
        boolean delete = messageBox.delete(userCommand.getId());
        if (delete) {
            System.out.println("Message " + userCommand.getId() + " was successfully removed!");
        } else {
            System.out.println("Message " + userCommand.getId() + " was not found!");
        }
    }

    private static void executeAdd(AddCommand userCommand, MessageBox messageBox) {
        messageBox.add(userCommand.getCategory(), userCommand.getSender(), userCommand.getAddress(), userCommand.getReceiver());
    }

    private static void executedEdit(EditCommand userCommand, MessageBox messageBox, Message message) {
        if (userCommand.getCategory() != null) {
            message.setCategory(userCommand.getCategory());
        }
        if (userCommand.getSender() != null) {
            message.setSender(userCommand.getSender());
        }
        if (userCommand.getReceiver() != null) {
            message.setReceiver(userCommand.getReceiver());
        }
        if (userCommand.getAddress() != null) {
            message.setAddress(userCommand.getAddress());
        }
    }
}
