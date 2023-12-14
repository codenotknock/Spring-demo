package org.codenotknock.mybatisPlus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.codenotknock.mybatisPlus.vo.domain.User;
import org.codenotknock.mybatisPlus.vo.dto.PageDTO;
import org.codenotknock.mybatisPlus.vo.query.QueryParam;
import org.codenotknock.mybatisPlus.vo.result.UserVO;

import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/14 1:59
 * @description
 */
public interface UserService extends IService<User> {
    Boolean deduction(Long id, int amount);
    List<UserVO>  querylist(QueryParam param);

    UserVO queryAndAddressById(Long id);
    List<UserVO>  queryAndAddressByIds(List<Long> ids);

    PageDTO<UserVO> pageQuery(QueryParam param);
    PageDTO<UserVO> pageQueryNew(QueryParam param);
}
