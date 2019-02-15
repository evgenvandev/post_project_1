package com.levelb.post;

import java.util.Scanner;

/**
 * Created by Администратор on 15.02.2019.
 */
public class UseConsoleInputReader {

    private Scanner scanner;

    public UseConsoleInputReader() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            String line = scanner.nextLine();
            parseAndExecutingLine(line);
        }
    }

    private void parseAndExecutingLine(String line) {
        Scanner s = new Scanner(line);
        // remove 2
        String token = s.next();
        if (token.equalsIgnoreCase("remove")) {
            if (s.hasNextLong()) {
                long l = s.nextLong();
                if (s.hasNext()) {
                    System.out.println("Unknown format: " + line);
                } else {
                    remove(l);
                }
            } else if (s.hasNext()) {
                System.out.println("Unknown format: " + line);
            } else {
                System.out.println("Wrong format: remove id - no id: " + line);
            }
        } else if (token.equalsIgnoreCase("exit")) {
            if (s.hasNext()) {
                System.out.println("Unknown format: " + line);
            } else {
                exit();
            }
        } else {
            processUnknownCommand(line);
        }
    }

    private void remove(long l) {
        System.out.println("REMOVING " + l);
    }

    private void processUnknownCommand(String line) {
        System.out.println("Unknown command: " + line);
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
