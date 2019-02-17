package com.levelb.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Администратор on 14.02.2019.
 */
public class MainOffice {

    private static final Logger log = LoggerFactory.getLogger(MainOffice.class);

    public boolean queue(Message message) {
        log.debug("Sending to main office: " + message);
        return true;
    }
}
