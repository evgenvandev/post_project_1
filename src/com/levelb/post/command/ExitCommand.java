package com.levelb.post.command;

import com.levelb.post.MessageBox;

/**
 * Created by Администратор on 15.02.2019.
 */
public class ExitCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
