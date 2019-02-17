package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

import java.util.List;

/**
 * Created by Администратор on 15.02.2019.
 */
public class SendCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {
        List<Long> longs = messageBox.sendToMainOffice();
        printer.println();
        printer.println("Following message were sent: ");
        for (Long id : longs) {
            printer.printAlign("Id", id);
        }
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "SendCommand{}";
    }
}
