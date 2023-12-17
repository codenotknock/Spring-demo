package org.codenotknock.secy.service.impl;

import org.codenotknock.secy.ResponseResult;
import org.codenotknock.secy.service.LoginService;
import org.codenotknock.secy.util.JWTUtil;
import org.codenotknock.secy.vo.domain.LoginUser;
import org.codenotknock.secy.vo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceimpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser user) {
        // AuthenticationManager 的 authenticate 方法进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        /*  authenticationManager.authenticate(authenticationToken);
        最终会调用到 UserDetailsService 中的方法
         */

        // 认证不通过时 authenticate 为 null
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 认证通过生成 jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String jwt_token = JWTUtil.createToken(loginUser);

        // 把用户信息存入redis
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt_token);
        redisCache.put("login"+id, loginUser);
        return ResponseResult.success("登录成功");
    }

    @Override
    public ResponseResult logOut() {
        // 获取 SecurityHolder 中的用户 id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();

        // 删除 redis 缓存
        redisCache.evict("login"+id);


        return ResponseResult.success("退出成功");
    }
}
