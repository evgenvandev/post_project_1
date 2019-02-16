package com.levelb.post.command;

/**
 * Created by Администратор on 15.02.2019.
 */
public class RemoveCommand implements UserCommand {
    private long id;

    public RemoveCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
