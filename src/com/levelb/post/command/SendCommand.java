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
        printer.println("Following message were sent: " + longs);
        printer.printSeparator();
    }

    @Override
    public String toString() {
        return "SendCommand{}";
    }
}
