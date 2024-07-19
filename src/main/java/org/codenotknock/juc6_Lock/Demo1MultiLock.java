package org.codenotknock.juc6_Lock;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author xiaofu
 * 多把锁 或者说 想对细粒度的锁
 */
@Slf4j(topic = "c.MultiLock")
public class Demo1MultiLock {
    public static void main(String[] args) {
        Room room = new Room();
        new Thread(() -> {
            try {
                room.sleeping();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小米").start();

        new Thread(() -> {
            try {
                room.studying();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "小明").start();
    }
}


@Slf4j(topic = "c.MultiLock_room")
class Room {
    private final Object sleepLock = new Object();
    private final Object studyLock = new Object();
    public void sleeping() throws InterruptedException {
        synchronized(sleepLock) {
            log.debug("sleep two hours...");
            sleep(2000);
        }
    }

    public void studying() throws InterruptedException {
        synchronized(studyLock) {
            log.debug("study two hours...");
            sleep(2000);
        }
    }
}
