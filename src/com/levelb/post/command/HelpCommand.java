package com.levelb.post.command;

import com.levelb.post.MessageBox;

/**
 * Created by Администратор on 15.02.2019.
 */
public class HelpCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox) {
        System.out.println("You can use following commands: ");
        System.out.println("add");
        System.out.println("send");
        System.out.println("list");
        System.out.println("edit <<id>>");
        System.out.println("remove <<id>>");
        System.out.println("help");
        System.out.println("exit");
    }
}
