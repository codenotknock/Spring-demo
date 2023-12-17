package org.codenotknock.secy.service;

import org.codenotknock.secy.ResponseResult;
import org.codenotknock.secy.vo.domain.SysUser;

public interface LoginService {

    ResponseResult login(SysUser user);

    ResponseResult logOut();
}
