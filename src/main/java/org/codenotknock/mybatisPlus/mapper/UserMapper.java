package org.codenotknock.mybatisPlus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.codenotknock.mybatisPlus.vo.domain.User;

/**
 * @author xiaofu
 * @date 2023/12/13 20:19
 * @description
 */


public interface UserMapper extends BaseMapper<User> {
    /* 自动实现增删改查的原理：
        mybatisPlus通过扫描实体类，并且基于反射获取实体类信息作为数据库表信息  比如上面的User，获取其字节码信息
        约定：
        - 类名驼峰转为下划线作为表名 UserInfo -->  user_info
        - 类的字段名驼峰转为下划线作为表的字段名 userName -->  user_name
    */


    // 自定义sql
    // 尽量别直接写 “ew”, 使用Constants.WRAPPER
    void updateBalanceByIds(@Param(Constants.WRAPPER)LambdaQueryWrapper<User> wrapper, @Param("amount") int amout);

    @Update("update user set banlance = banlance - #{amount} where id = #{id}}")
    Boolean deductBanlance(Long id, Integer amount);
}
