package org.codenotknock.test;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.codenotknock.mybatisPlus.service.UserService;
import org.codenotknock.mybatisPlus.vo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/14 2:00
 * @description
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void query1() {
        userService.listByIds(Arrays.asList(2L, 3L, 5L));
    }

    @Test
    public void testSaveOneByOne() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            userService.save(new User());
            // 每一次插入都是一次网络请求
        }
        long end = System.currentTimeMillis();
        System.out.println(end - st);
    }

    @Test
    public void saveBatch() {
        // 每次 批量插入1000条 插入 100 次
        ArrayList<User> list = new ArrayList<>(1000);
        long st = System.currentTimeMillis();
        for (int t = 0; t < 100; t++){
            for (int i = 0; i < 1000; i++) {
                list.add(new User());
            }
            // 效率相对于每次插入快10倍
            // 基于jdbc预编译处理 1000条sql语句，所以还是有点慢
            userService.saveBatch(list);

            // 在url中开启重写批处理配置 rewriteBatchedStatements=true;
            // 实现了真正的批处理，不再是1000条sql 而是拼接成一条sql语句
            // 10w条从4分多钟 提高到6s中的执行速度

        }
        long end = System.currentTimeMillis();
        System.out.println(end - st);
    }

    @Test
    public void pageUser() {
        int pageNo = 1, pageSize = 20;
        // 分页参数
        Page<User> page = Page.of(pageNo, pageSize);
        // 排序条件 true升序，false降序
        page.addOrder(new OrderItem("balance", false));
        // 分页查询
        Page<User> userPage = userService.page(page);
        // 总数 和 总页数
        Long total = userPage.getTotal();
        Long pages = userPage.getPages();
        // 当前页的数据列表
        List<User> records = page.getRecords();
    }


}
