package org.codenotknock.mybatisPlus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author xiaofu
 * @date 2023/12/14 23:40
 * @description
 */
@Getter
public enum UserStatusEnum {
    NORMAL(1, "正常"),
    FREEZE(2, "冻结"),

    ;

    @EnumValue
    @JsonValue
    private int value;

    private String desc;

    private UserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
