package org.codenotknock.juc2;

import lombok.extern.slf4j.Slf4j;
import org.codenotknock.juc2.util.FileReader;


/**
 * 异步
 */

@Slf4j(topic = "c.Async")
public class Async {

    public static void main(String[] args) {
        new Thread(() -> FileReader.read(Cons.Path1)).start();
        log.debug("doing other things ... ");
    }
}
