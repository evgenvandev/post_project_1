package com.levelb.post.command;

import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 16.02.2019.
 */
public class EmptyCommand implements UserCommand {
    @Override
    public void execute(PostBusinessLogic logic, OutputPrinter printer) {

    }

    @Override
    public String toString() {
        return "EmptyCommand{}";
    }
}
