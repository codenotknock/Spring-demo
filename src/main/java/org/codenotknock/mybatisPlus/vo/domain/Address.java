package org.codenotknock.mybatisPlus.vo.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author xiaofu
 * @date 2023/12/13 20:53
 * @description
 */
@Data
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("town")
    private String town;

    @TableField("mobile")
    private String mobile;

    @TableField("street")
    private String street;

    @TableField("contact")
    private String contact;

    @TableField("is_default")
    private Boolean isDefault;

    @TableField("notes")
    private String notes;

    @TableField("deleted")
    @TableLogic
    private Boolean deleted;
}
