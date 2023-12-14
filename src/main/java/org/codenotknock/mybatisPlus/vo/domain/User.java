package org.codenotknock.mybatisPlus.vo.domain;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaofu
 * @date 2023/12/13 20:57
 * @description
 *
 */
@Data
@TableName("user")
public class User {
    /* mybatisPlus继承BaseMapper后，自动实现增删改查的原理：
    mybatisPlus通过扫描实体类，并且基于反射获取实体类信息作为数据库表信息  比如上面的User，获取其字节码信息
    约定：
    - 类名驼峰转为下划线作为表名 UserInfo -->  user_info
    - 类的字段名驼峰转为下划线作为表的字段名 userName -->  user_name
*/
    /*
     * @TableName("user") 表名
     * @TableId(type = IdType.AUTO)  表中主键字段
     * @TableField(value = "username")  表中普通字段，如果相同也可以不标记
     IdType枚举
        - AUTO 数据库自增长
        - INPUT 通过set方法自行输入
        - ASSIGN_ID 分配ID，接口IdentifierGenerator的方法nextId生产id 雪花算法
      @TableField
        - 成员变量名与表字段名不一致时必须映射
        - 成员变量名是布尔值且is开头，必须映射
        - 是关键字，比如order，应该转义@TableField(value = "`order`")
        - 不是数据库字段名,使用 @TableField(exist = false) 进行忽略
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "info")
    private String info;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "balance")
    private Integer balance;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}