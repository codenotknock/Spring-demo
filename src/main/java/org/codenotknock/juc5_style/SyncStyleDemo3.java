package org.codenotknock.juc5_style;

import lombok.extern.slf4j.Slf4j;
import org.codenotknock.juc2.util.Sleeper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaofu
 * 同步模式之保护性暂停
 * Guarded Suspension，  用在一个线程等待另一个线程的执行结果
 * 多任务版：模拟信箱、收信
 * 邮递员、信箱、居民
 */

@Slf4j(topic = "c.SyncStyle_Guarded3")
public class SyncStyleDemo3 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new People().start();
        }
        Sleeper. sleep(1);
        Set<Integer> ids = MailBoxes.getIds();
        ids.forEach(id -> {
            new Postman(id, "内容"+id).start();
        });
    }
}

@Slf4j(topic = "c.SyncStyle_Guarded3_Postman")
class Postman extends Thread {
    private int id;
    private String mail;
    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }
    @Override
    public void run() {
        // 送信
        GuardedObject3 guardObject3 = MailBoxes.getGuardObject3(id);
        log.debug("开始送信 id:{}, 内容：{}", id, mail);
        guardObject3.complete(mail);
        log.debug("送信完成 id:{}", id);

    }
}
@Slf4j(topic = "c.SyncStyle_Guarded3_People")
class People extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedObject3 guardedObject3 = MailBoxes.createGuardObject3();
        log.debug("开始收信 id:{}", guardedObject3.getId());
        Object mail = guardedObject3.get(5000L);
        log.debug("收到信  id:{}，内容：{}", guardedObject3.getId(), mail);
    }
}



class MailBoxes {
    // 用户收完信后需要被移除！
    private static Map<Integer, GuardedObject3> boxes = new ConcurrentHashMap<>();

    private static int id = 1;
    public static synchronized int generateId() {
        return id++;
    }
    public static GuardedObject3 createGuardObject3() {
        GuardedObject3 g = new GuardedObject3(generateId());
        boxes.put(g.getId(), g);
        return g;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }

    public static GuardedObject3 getGuardObject3(int id) {
        return boxes.remove(id);
    }

}

class GuardedObject3 extends GuardedObject2 {
    // 标识 Guarded Object
    private Integer id;

    public GuardedObject3(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

