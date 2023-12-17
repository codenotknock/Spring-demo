package org.codenotknock.secy.service.impl;

import org.codenotknock.secy.vo.domain.SysUser;
import org.codenotknock.secy.mapper.SysUserMapper;
import org.codenotknock.secy.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author xiaofu
 * @since 2023-12-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
