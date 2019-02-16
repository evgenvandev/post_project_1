package com.levelb.post;

import com.levelb.post.command.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Администратор on 15.02.2019.
 */
public class UseConsoleInputReader {

    private Scanner scanner;

    public UseConsoleInputReader() {
        scanner = new Scanner(System.in);
    }

    public UserCommand nextCommand() {
        Scanner s = new Scanner(scanner.nextLine());
        String token = s.next();
        if (token.equalsIgnoreCase("remove")) {
            return parseRemove(s);
        } else if (token.equalsIgnoreCase("edit")) {
            return parseEdit(s);
        } else if (token.equalsIgnoreCase("add")) {
            return parseAdd(s);
        } else if (token.equalsIgnoreCase("send")) {
            if (!s.hasNext()) {
                return new SendCommand();
            }
        } else if (token.equalsIgnoreCase("list")) {
            if (!s.hasNext()) {
                return new ListCommand();
            }
        } else if (token.equalsIgnoreCase("help")) {
            if (!s.hasNext()) {
                return new HelpCommand();
            }
        } else if (token.equalsIgnoreCase("exit")) {
            if (!s.hasNext()) {
                return new ExitCommand();
            }
        }
        return null;
    }

    private AddCommand parseAdd(Scanner s) {
        if (s.hasNext()) {
            return null;
        }
        System.out.println("Please, enter sender: ");
        String sender = scanner.nextLine();
        System.out.println("Please, enter receive: ");
        String receiver = scanner.nextLine();
        System.out.println("Please, enter address: ");
        String address = scanner.nextLine();
        System.out.println("Please, enter category from: " + Arrays.toString(Message.MessageCategory.values()));
        String category = scanner.nextLine().trim();
        Message.MessageCategory cat = parseCategory(category);
        return new AddCommand(sender, receiver, address, cat);
    }

    private EditCommand parseEdit(Scanner s) {
        if (s.hasNextLong()) {
            long l = s.nextLong();
            if (!s.hasNext()) {
                return new EditCommand(l);
            }
        }
        return null;
    }

    private RemoveCommand parseRemove(Scanner s) {
        if (s.hasNextLong()) {
            long l = s.nextLong();
            if (!s.hasNext()) {
                return new RemoveCommand(l);
            }
        }
        return null;
    }

    public void readEdit(EditCommand command, Message message) {
        System.out.print("Change sender (" + message.getSender() + ") if you want: ");
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
            command.setSender(line);
        }

        System.out.print("Change receiver (" + message.getReceiver() + ") if you want: ");
        line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
            command.setReceiver(line);
        }

        System.out.print("Change address (" + message.getAddress() + ") if you want: ");
        line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
            command.setAddress(line);
        }

        System.out.print("Change category (" + message.getCategory() + ") if you want: ");
        line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
            command.setCategory(parseCategory(line));
        }
    }

    private Message.MessageCategory parseCategory(String category) {
        Message.MessageCategory cat;
        switch (category) {
            case "first_class":
                cat = Message.MessageCategory.FIRST_CLASS;
                break;
            case "special":
                cat = Message.MessageCategory.SPECIAL;
            case "regular":
            default:
                cat = Message.MessageCategory.REGULAR;
                break;
        }
        return cat;
    }
}
