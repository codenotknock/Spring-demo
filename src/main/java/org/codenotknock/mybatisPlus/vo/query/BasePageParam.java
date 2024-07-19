package org.codenotknock.mybatisPlus.vo.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiaofu
 * @date 2023/12/15 0:35
 * @company: www.dtstack.com
 */

@Data
@ApiModel(description = "分页查询")
public class BasePageParam {
    @ApiModelProperty("当前页")
    private Integer currentPage = 1;

    @ApiModelProperty("总页数")
    private Integer pageSize = 10;

    @ApiModelProperty("排序字段")
    private String sortBy;

    @ApiModelProperty("是否升序")
    private Boolean isAsc;

    public <T> Page<T> toMpPage(OrderItem ... items) {
        Page<T> page = Page.of(currentPage, pageSize);
        if (StrUtil.isNotBlank(sortBy)) {
            page.addOrder(new OrderItem(sortBy, isAsc));
        } else if (null != items) {
            page.addOrder(items);
        }
        return page;
    }



}
