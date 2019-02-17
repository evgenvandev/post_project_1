package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 16.02.2019.
 */
public class EmptyCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox, OutputPrinter printer) {

    }

    @Override
    public String toString() {
        return "EmptyCommand{}";
    }
}
