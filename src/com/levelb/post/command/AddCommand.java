package com.levelb.post.command;

import com.levelb.post.Message;
import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 15.02.2019.
 */
public class AddCommand implements UserCommand {
    private String sender;
    private String receiver;
    private String address;
    private Message.MessageCategory category;

    public AddCommand(String sender, String receiver, String address, Message.MessageCategory category) {
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.category = category;
    }

    @Override
    public void execute(PostBusinessLogic logic, OutputPrinter printer) {
        long id = logic.add(category, sender, address, receiver);
        printer.println();
        printer.println("Added message:");
        printer.print(logic.search(id));
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "AddCommand{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", category=" + category +
                '}';
    }
}
