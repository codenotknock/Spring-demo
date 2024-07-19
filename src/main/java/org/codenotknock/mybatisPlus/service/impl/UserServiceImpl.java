package org.codenotknock.mybatisPlus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.codenotknock.mybatisPlus.vo.domain.Address;
import org.codenotknock.mybatisPlus.vo.domain.User;
import org.codenotknock.mybatisPlus.mapper.UserMapper;
import org.codenotknock.mybatisPlus.service.UserService;
import org.codenotknock.mybatisPlus.vo.dto.PageDTO;
import org.codenotknock.mybatisPlus.vo.query.QueryParam;
import org.codenotknock.mybatisPlus.vo.result.AddressVO;
import org.codenotknock.mybatisPlus.vo.result.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 * @date 2023/12/14 2:00
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Boolean deduction(Long id, int amount) {
        User user = this.baseMapper.selectById(id);
        if (Objects.equals(user.getStatus(), 2)) {
            throw new RuntimeException("用户状态异常");
        }

        if (user.getBalance() < amount) {
            throw new RuntimeException("用户余额不足");
        }
        this.baseMapper.deductBanlance(id, amount);
        return Boolean.TRUE;
    }

    @Override
    public List<UserVO> querylist(QueryParam param) {
        List<User> users = this.lambdaQuery().like(param.getName() != null, User::getBalance, param.getName())
                .eq(param.getStatus() != null, User::getStatus, param.getStatus())
                .gt(param.getMinBalance() != null, User::getBalance, param.getMinBalance())
                .lt(param.getMaxBalance() != null, User::getBalance, param.getMaxBalance()).list();
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @Override
    public UserVO queryAndAddressById(Long id) {
        User user = this.baseMapper.selectById(id);
        if (null == user || Objects.equals(2, user.getStatus())) {
            throw new RuntimeException("用户信息错误！！");
        }
        // 静态方法Db 避免 spring 循环依赖的问题
        List<org.codenotknock.mybatisPlus.vo.domain.Address> addressList = Db.lambdaQuery(org.codenotknock.mybatisPlus.vo.domain.Address.class)
                .eq(org.codenotknock.mybatisPlus.vo.domain.Address::getUserId, id)
                .list();
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if (CollUtil.isNotEmpty(addressList)) {
            List<AddressVO> addressVOList = BeanUtil.copyToList(addressList, AddressVO.class);
            userVO.setAddress(addressVOList);
        }

        return userVO;
    }

    @Override
    public List<UserVO> queryAndAddressByIds(List<Long> ids) {
        List<User> users = this.listByIds(ids);
        if (CollUtil.isEmpty(users)) {
            throw new RuntimeException("用户不存在！");
        }

        List<Long> finalids = users.stream().map(User::getId).collect(Collectors.toList());
        List<org.codenotknock.mybatisPlus.vo.domain.Address> addressList = Db.lambdaQuery(org.codenotknock.mybatisPlus.vo.domain.Address.class)
                .in(Address::getUserId, finalids)
                .list();
        Map<Long, List<AddressVO>> map = new HashMap<>();
        if (CollUtil.isNotEmpty(addressList)) {
            List<AddressVO> addressVOS = BeanUtil.copyToList(addressList, AddressVO.class);
            // 对address按照userid分组
            map = addressVOS.stream().collect(Collectors.groupingBy(AddressVO::getUserId));
        }
        Map<Long, List<AddressVO>>  userIdAddressMap = map;
        List<UserVO> list = new ArrayList<>(users.size());
        users.forEach(user -> {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            userVO.setAddress(userIdAddressMap.get(user.getId()));
            list.add(userVO);
        });

        return list;
    }

    @Override
    public PageDTO<UserVO> pageQuery(QueryParam param) {
        // 构建查询条件
        Page<User> page = Page.of(param.getCurrentPage(), param.getPageSize());
        page.addOrder(new OrderItem(param.getSortBy(), param.getIsAsc()));

        // 分页查询
        IPage<User> userIPage = this.lambdaQuery().like(param.getName() != null, User::getBalance, param.getName())
                .eq(param.getStatus() != null, User::getStatus, param.getStatus())
                .gt(param.getMinBalance() != null, User::getBalance, param.getMinBalance())
                .lt(param.getMaxBalance() != null, User::getBalance, param.getMaxBalance())
                .page(page);

        // 封装返回结果
        List<User> records = userIPage.getRecords();
        PageDTO<UserVO> pageDTO = new PageDTO<>();
        pageDTO.setPageSize((int) userIPage.getPages());
        pageDTO.setTotal((int) userIPage.getTotal());
        if (CollUtil.isNotEmpty(records)) {
            List<UserVO> userVOS = BeanUtil.copyToList(records, UserVO.class);
            pageDTO.setData(userVOS);
        }
        return pageDTO;
    }

    @Override
    public PageDTO<UserVO> pageQueryNew(QueryParam param) {
        Page<User> page = param.toMpPage();

        // 条件查询
        IPage<User> userIPage = this.lambdaQuery().like(param.getName() != null, User::getBalance, param.getName())
                .eq(param.getStatus() != null, User::getStatus, param.getStatus())
                .gt(param.getMinBalance() != null, User::getBalance, param.getMinBalance())
                .lt(param.getMaxBalance() != null, User::getBalance, param.getMaxBalance())
                .page(page);

//        return PageDTO.transfer(userIPage, UserVO.class);
//        return PageDTO.transferNew(userIPage, user -> BeanUtil.copyProperties(user, UserVO.class));
        return PageDTO.transferNew(userIPage, user -> {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            // 可以做一些其它逻辑
            return userVO;
        });
    }
}
