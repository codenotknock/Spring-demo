package org.codenotknock.secy.controller;

import org.codenotknock.secy.ResponseResult;
import org.codenotknock.secy.service.LoginService;
import org.codenotknock.secy.vo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser user) {
        // 登录
        return loginService.login(user);
    }
}
