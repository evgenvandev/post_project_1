package com.levelb.post;

import com.levelb.post.command.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

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
        if (s.hasNext()) {
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
        } else {
            return new EmptyCommand();
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
        Message.MessageCategory cat = readCategory();
        return new AddCommand(sender, receiver, address, cat);
    }

    private Message.MessageCategory readCategory() {
        Message.MessageCategory cat;
        while (true) {
            String category = scanner.nextLine().trim();
            cat = convertToCategory(category);
            if (cat != null) {
                break;
            } else {
                System.out.println("Please, enter category from: " + Arrays.toString(Message.MessageCategory.values()));
            }
        }
        return cat;
    }

    private Message.MessageCategory convertToCategory(String category) {
        switch (category) {
            case "first_class":
                return Message.MessageCategory.FIRST_CLASS;
            case "special":
                return Message.MessageCategory.SPECIAL;
            case "regular":
                return Message.MessageCategory.REGULAR;
            default:
                return null;
        }
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

    public void readEdit(EditCommand command, String sender, String receiver, String address, Message.MessageCategory category) {
        System.out.print("Change sender (" + sender + ") if you want: ");
        command.setSender(readOrSkip(sender));

        System.out.print("Change receiver (" + receiver + ") if you want: ");
        command.setReceiver(readOrSkip(receiver));

        System.out.print("Change address (" + address + ") if you want: ");
        command.setAddress(readOrSkip(address));

        System.out.print("Change category (" + category + ") if you want: ");
        command.setCategory(readOrSkip(category, this::convertToCategory, category1 -> category1 !=null));
    }

    private String readOrSkip(String previous) {
        return readOrSkip(previous, t -> t, t -> true);
    }

    private <T> T readOrSkip(T previous, Function<String, T> convert, Function<T, Boolean> validate) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()) {
            T result = convert.apply(line);
            if (validate.apply(result)) {
                return result;
            } else {
                System.out.println("Wrong value, please repeat!");
            }
        }
        return previous;
    }
}
