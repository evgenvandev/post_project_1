package com.levelb.post.command;

import com.levelb.post.MessageBox;

import java.util.List;

/**
 * Created by Администратор on 15.02.2019.
 */
public class SendCommand implements UserCommand {
    @Override
    public void execute(MessageBox messageBox) {
        List<Long> longs = messageBox.sendToMainOffice();
        System.out.println("Following message were sent: " + longs);
    }
}
