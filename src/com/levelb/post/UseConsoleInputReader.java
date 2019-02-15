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
            // remove 2
            if (line.toLowerCase().startsWith("remove")) {
                String[] arr = line.trim().split(" ");
                if (arr.length == 2) {
                    long l = Long.parseLong(arr[1]);
                    remove(l);
                } else {
                    System.out.println("Unknown format: " + line);
                }
            } else if (line.equalsIgnoreCase("exit")) {
                exit();
            } else {
                processUnknownCommand(line);
            }
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
