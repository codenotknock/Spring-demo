package org.codenotknock.mybatisPlus.vo.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 * @date 2023/12/15 0:40
 * @description
 */
@Data
@ApiModel(description = "分页结果")
public class PageDTO <T>{
    @ApiModelProperty("总个数")
    private Integer total;

    @ApiModelProperty("总页数")
    private Integer pageSize;

    @ApiModelProperty("结果")
    private List<T> data;

    // T --> VO   E --> domain
    public static <T, E> PageDTO<T> transfer(IPage<E> page, Class<T> clazz) {

        PageDTO<T> dto = new PageDTO<>();
        dto.setPageSize((int) page.getPages());
        dto.setTotal((int) page.getTotal());

        List<E> records = page.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            // 这里是泛型的转换
            dto.setData(BeanUtil.copyToList(records, clazz));
        }
        return dto;
    }



    public static <T, E> PageDTO<T> transferNew(IPage<E> page, Function<E, T> convertor) {
        /*  T --> VO   E --> domain
        Function 函数式接口编程  convertor 将E对象转换为V对象

        **/
        PageDTO<T> dto = new PageDTO<>();
        dto.setPageSize((int) page.getPages());
        dto.setTotal((int) page.getTotal());

        List<E> records = page.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            // 这里是泛型的转换:stream做流的处理并收集结果，convertor 自己进行转换
            List<T> list = records.stream().map(convertor).collect(Collectors.toList());
            dto.setData(list);
        }
        return dto;
    }

}
