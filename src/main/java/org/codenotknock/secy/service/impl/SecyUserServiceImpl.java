package org.codenotknock.secy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.codenotknock.secy.mapper.SysMenuMapper;
import org.codenotknock.secy.mapper.SysUserMapper;
import org.codenotknock.secy.vo.domain.LoginUser;
import org.codenotknock.secy.vo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SecyUserServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, username));
        if (null == sysUser || Objects.equals(1, sysUser.getStatus())) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }

        // TODO 查询对应的权限信息
        List<String> list = sysMenuMapper.selectPermsByUserId(sysUser.getId());


        return new LoginUser(sysUser, list);
    }
}
