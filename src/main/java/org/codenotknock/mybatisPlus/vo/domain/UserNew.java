package org.codenotknock.mybatisPlus.vo.domain;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.codenotknock.mybatisPlus.UserStatusEnum;

import java.time.LocalDateTime;


@Data
// autoResultMap 开启结果集映射
@TableName(value = "user", autoResultMap = true)
public class UserNew {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(typeHandler = JacksonTypeHandler.class, value = "info")
    // 使用 JacksonTypeHandler 处理字段结果值
    private UserInfo info;

    @TableField(value = "status")
    private UserStatusEnum status;

    @TableField(value = "balance")
    private Integer balance;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}