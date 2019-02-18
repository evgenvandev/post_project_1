package com.levelb.post.command;

import com.levelb.post.OutputPrinter;
import com.levelb.post.PostBusinessLogic;

/**
 * Created by Администратор on 15.02.2019.
 */
public interface UserCommand {
    void execute(PostBusinessLogic logic, OutputPrinter printer);
}
