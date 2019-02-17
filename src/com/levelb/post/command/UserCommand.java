package com.levelb.post.command;

import com.levelb.post.MessageBox;
import com.levelb.post.OutputPrinter;

/**
 * Created by Администратор on 15.02.2019.
 */
public interface UserCommand {
    void execute(MessageBox messageBox, OutputPrinter printer);
}
