package org.codenotknock.mybatisPlus.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaofu
 * @date 2023/12/14 20:14
 */

@EqualsAndHashCode(callSuper = true)
// 在生成 equals() 和 hashCode() 方法时需要考虑父类的属性
@Data
@ApiModel(description = "用户查询条件实体")
public class QueryParam extends BasePageParam{
    @ApiModelProperty("用户名关键字")
    private String name;

    @ApiModelProperty("用户状态：1-正常，2-冻结")
    private Integer status;

    @ApiModelProperty("余额最小值")
    private Integer minBalance;

    @ApiModelProperty("余额最大值")
    private Integer maxBalance;
}
