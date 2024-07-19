package org.codenotknock.test;

import java.util.LinkedList;
import java.util.List;

public class StopWorldDemo1 {

    public static void main(String[] args) {
        new PrintThread().start();
        new ObjectThread().start();
    }

}

class PrintThread extends Thread{

    @Override
    public void run() {
        long last = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            System.out.println(now - last);
            last = now;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ObjectThread extends Thread {

    @Override
    public void run() {
        List<byte[]> bytes = new LinkedList<>();

        while(true) {
            if (bytes.size() >= 50) {
                bytes.clear();
            }
            bytes.add(new byte[100 * 1024 * 1024]);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}