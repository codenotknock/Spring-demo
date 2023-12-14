package org.codenotknock.mybatisPlus.vo.result;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiaofu
 * @date 2023/12/14 19:45
 * @description
 */
@Data
@ApiModel(description = "用户信息")
public class UserVO{
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "信息")
    private String info;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "余额")
    private Integer balance;

    @ApiModelProperty(value = "收货地址")
    private List<AddressVO> address;
}
