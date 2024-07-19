package org.codenotknock.juc2;

import lombok.extern.slf4j.Slf4j;
import org.codenotknock.juc2.util.FileReader;

import java.io.IOException;

/**
 * 同步
 */

@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) throws IOException {
        FileReader.read(Cons.Path1);
        log.debug("doing other things ... ");
    }
}