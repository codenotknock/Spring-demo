package org.codenotknock.mybatisPlus.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codenotknock.mybatisPlus.vo.domain.User;
import org.codenotknock.mybatisPlus.vo.dto.PageDTO;
import org.codenotknock.mybatisPlus.vo.dto.UserFormDTO;
import org.codenotknock.mybatisPlus.service.UserService;
import org.codenotknock.mybatisPlus.vo.query.QueryParam;
import org.codenotknock.mybatisPlus.vo.result.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaofu
 * @description
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户管理接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public Boolean saveUser(@RequestBody UserFormDTO userFormDTO) {
        userService.save(BeanUtil.copyProperties(userFormDTO, User.class));
        return Boolean.TRUE;
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id) {
        userService.removeById(id);
        return Boolean.TRUE;
    }

    @ApiOperation("查询用户")
    @PostMapping("/select/{id}")
    public UserVO getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }


    @ApiOperation("批量查询用户")
    @PostMapping("/selectUsers")
    public List<UserVO> getUsers(@RequestParam("ids") List<Long> ids) {
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @ApiOperation("用户减少余额")
    @PostMapping("/{id}/deduction/{amount}")
    public Boolean deduction(@PathVariable("id") Long id, @PathVariable("amount") int amount) {
        return userService.deduction(id, amount);
    }

    @ApiOperation("根据条件查询用户")
    @PostMapping("/list")
    public List<UserVO> list(@RequestBody QueryParam param) {
        List<UserVO> users = userService.querylist(param);
        return BeanUtil.copyToList(users, UserVO.class);
    }


    @ApiOperation("查询用户")
    @PostMapping("/select/{id}")
    public UserVO getUserbyId(@PathVariable("id") Long id) {
        return userService.queryAndAddressById(id);
    }

    @ApiOperation("根据条件分页查询")
    @PostMapping("/page")
    public PageDTO<UserVO> pageQuery(@RequestBody QueryParam param) {
        return userService.pageQuery(param);
    }
}
