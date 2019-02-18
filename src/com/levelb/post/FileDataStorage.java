package com.levelb.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 17.02.2019.
 */
public class FileDataStorage {
    private static final Logger log = LoggerFactory.getLogger(FileDataStorage.class);
    public static final String DELIMITER = "\t";

    private String fileName;

    public FileDataStorage(String fileName) {
        this.fileName = fileName;
    }

    public void save(List<Message> messages) {
        try(PrintWriter writer = new PrintWriter(Paths.get(fileName).toFile())){
            for (Message message : messages) {
                writer.print(message.getId());
                writer.print(DELIMITER);
                writer.print(message.getCategory());
                writer.print(DELIMITER);
                writer.print(message.getSender());
                writer.print(DELIMITER);
                writer.print(message.getReceiver());
                writer.print(DELIMITER);
                writer.print(message.getAddress());
                writer.print(DELIMITER);
                writer.print(message.getDate().getTime());
                writer.println();
            }
        } catch (FileNotFoundException e) {
            log.error("Error writing file:", e);
        }
    }

    public List<Message> read() {
        List<Message> messages = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] tokens = line.split(DELIMITER);
                Long id = Long.parseLong(tokens[0]);
                Message.MessageCategory category = Message.MessageCategory.valueOf(tokens[1]);
                Date date = new Date(Long.parseLong(tokens[5]));
                messages.add(new Message(id, category, tokens[2], tokens[4], tokens[3], date));
            }
        } catch (IOException e) {
            log.error("Error reading file:", e);
        }
        return messages;
    }
}
