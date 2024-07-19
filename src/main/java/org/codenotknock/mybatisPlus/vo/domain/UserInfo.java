package org.codenotknock.mybatisPlus.vo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfo {
    private Integer age;
    private String info;
}
