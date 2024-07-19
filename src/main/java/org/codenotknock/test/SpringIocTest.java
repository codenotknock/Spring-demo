package org.codenotknock.test;

import org.codenotknock.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {
    public static void main(String [] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
       /* 1. 判断名字是否合法（不为空）
          2.    1.1 public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
                    this(new String[]{configLocation}, true, (ApplicationContext)null);
                }
                   1.1.1 public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
                        super(parent);
                        this.setConfigLocations(configLocations);
                        if (refresh) {
                            this.refresh();
                       }

    }

        */
        System.out.println("启动启动！！！...");
        MessageService messageService = applicationContext.getBean(MessageService.class);
        System.out.println(messageService.getMessage());
    }
}
