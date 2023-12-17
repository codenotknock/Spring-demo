package org.codenotknock.secy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回响应，统一封装实体
 *
 * @author xiafu
 * @param <T> 数据实体泛型
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value= "返回响应", description = "返回响应，统一封装实体")
public class ResponseResult<T> {
    /**
     * 错误码<br>
     */
    @ApiModelProperty("错误码")
    private String errorCode;

    /**
     * 错误信息<br>
     */
    @ApiModelProperty( "错误信息")
    private String errorMessage;

    /**
     * 数据实体（泛型）<br>。
     */
    @ApiModelProperty( "数据实体（泛型）")
    private T data;


    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>( null, null, data);
    }


    public static <T> ResponseResult<T> fail(String userMessage, String errorCode, String errorMessage) {
        return new ResponseResult<>(errorCode, errorMessage, null);
    }

}



