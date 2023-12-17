package org.codenotknock.secy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.codenotknock.secy.mapper.SysUserMapper;
import org.codenotknock.secy.vo.domain.LoginUser;
import org.codenotknock.secy.vo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SecyUserServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        if (null == sysUser || Objects.equals(0, sysUser.getEnabled())) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }

        // TODO 查询对应的权限信息



        return new LoginUser(sysUser);
    }
}
