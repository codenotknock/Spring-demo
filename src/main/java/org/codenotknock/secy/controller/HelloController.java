package org.codenotknock.secy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofu
 * @date 2023/12/17 2:14
 * @description
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('sys:dept:list')")
    @PreAuthorize("@ex.hasAuthority('sys:dept:list')")
    public String hello() {
        return "hello";
    }

    /* hasAnyAuthority 授权
        private boolean hasAnyAuthorityName(String prefix, String... roles) {
        Set<String> roleSet = this.getAuthoritySet();
        String[] var4 = roles;
        int var5 = roles.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String role = var4[var6];
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }

        return false;
    }
     */
}
