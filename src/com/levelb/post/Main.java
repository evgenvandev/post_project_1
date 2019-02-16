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
                consoleInputReader.readEdit(command, message.getSender(), message.getReceiver(), message.getAddress(), message.getCategory());
            }
        }
        userCommand.execute(messageBox);
    }
}
