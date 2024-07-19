package org.codenotknock.service.impl;

import org.codenotknock.service.MessageService;


/**
 * 一切为了Spring IOC 源码  版本4.3.11.RELEASE
 * @author xiaofu
 * @date 2023/11/8 11:15
 */

public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "Spring IOC 源码  来喽！！！";
    }
}
