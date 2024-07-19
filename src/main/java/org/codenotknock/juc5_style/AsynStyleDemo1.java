package org.codenotknock.juc5_style;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author xiaofu
 * 线程间的消息模拟
 * 生产者/消费者  消息队列
 */

@Slf4j(topic = "c.Asynchronous1")
public class AsynStyleDemo1 {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(6);
        for (int i = 0; i < 5; i++) {
            int id  = i;
            new Thread(()-> {
                queue.put(new Message(id, "内容"+id));
            }, "Producer"+i).start();
        }

        new Thread(() -> {
            while(true) {
                queue.take();
            }
        }, "Customer").start();
    }
}
// 消息队列类，Java线程之间的通信
@Slf4j(topic = "c.MessageQueue")
class MessageQueue {
    // 双向链表：表示消息队列
    private LinkedList<Message> list = new LinkedList<>();

    // 消息容量大小
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
        list = new LinkedList<>();
    }



    // 存入消息
    public void put(Message message) {
        synchronized(list) {
            while(list.size() == capacity) {
                try {
                    log.debug("消息队列满了，生产者等待中...");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头获取消息，队尾存消息
            list.addLast(message);
            log.debug("生产者生产成功：{}...", message);
            list.notifyAll();
        }

    }


    // 获取消息
    public Message take() {
        // 检查消息是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("消息为空，消费者等待中....");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头获取消息，队尾存消息
            Message message = list.removeFirst();
            log.debug("消费者消费：{}", message);
            list.notifyAll();

            return message;
        }

    }

}

// 消息
@Slf4j(topic = "c.Message")
final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }


    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}