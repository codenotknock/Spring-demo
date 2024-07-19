package org.codenotknock.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.codenotknock.mybatisPlus.vo.domain.User;
import org.codenotknock.mybatisPlus.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void queryById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    @Test
    void queryById2() {
        System.out.println("sadasdas");
    }

    @Test
    void query1() {
        // 利用 QueryWrapper 查询   模糊查询名字 like "fu"  balance > 1000 的
        List<User> users = userMapper.selectList(new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "fu")
                .ge("banlance", 1000));

        users.forEach(System.out::println);

        System.out.println("sadasdas");
    }

    @Test
    void update2() {
        // QueryWrapper  把 xiaofu 的 balance 更新为 2000
        User user = new User();
        user.setBalance(2000);
        userMapper.update(user, new QueryWrapper<User>()
                .eq("username", "xiaofu"));

        User userNew = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", "xiaofu"));
        System.out.println(userNew);
    }

    @Test
    void update3() {
        // UpdateWrapper  把 id 为 [2, 3, 6] 的 balance 减去 200
        userMapper.update(null, new UpdateWrapper<User>()
                .setSql("banlance = banlance - 200")
                .in("id", Arrays.asList(2L, 3L, 6L)));
    }

    // QueryWrapper、UpdateWrapper 这种属于硬编码，不太实用的
    // LambdaQueryWrapper、LambdaUpdateWrapper  拉姆达这种避免了硬编码的写法，尽量减少硬编码的写法
    @Test
    void query4() {
        // 利用 LambdaQueryWrapper 查询   模糊查询名字 like "fu"  balance > 1000 的
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getBalance)
                .like(User::getUsername, "fu")
                .ge(User::getBalance, 1000));
        userList.forEach(System.out::println);
    }

    @Test
    void update5() {
        // LambdaQueryWrapper  把 xiaofu 的 balance 更新为 2000
        User user = new User();
        user.setBalance(2000);
        userMapper.update(user, new LambdaQueryWrapper<User>()
                .eq(User::getUsername, "xiaofu"));

        User userNew = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, "xiaofu"));
        System.out.println(userNew);
    }

    @Test
    void update6() {
        // LambdaUpdateWrapper  把 id 为 [2, 3, 6] 的 balance 减去 200
        userMapper.update(null, new LambdaUpdateWrapper<User>()
                .setSql("banlance = banlance - 200")
                .in(User::getId, Arrays.asList(2L, 3L, 6L)));
    }


    @Test
    void customSql7() {
        List<Long> ids = Arrays.asList(1L, 2L);
        int amount = 200;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().in(User::getId, ids);
        userMapper.updateBalanceByIds(queryWrapper, amount);
    }


}
