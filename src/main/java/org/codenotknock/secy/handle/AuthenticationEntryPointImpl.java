package org.codenotknock.secy.handle;

import com.alibaba.fastjson.JSON;
import org.codenotknock.secy.ResponseResult;
import org.codenotknock.secy.util.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author xiaofu
 * 认证失败的处理：统一 json 格式返回给前端
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
/*
    如果是认证过程中出现的异常会被封装成 AuthenticationException
    然后调用 AuthenticationEntryPoint 对象的 commence 方法去进行异常处理
 */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.fail(null, String.valueOf(HttpStatus.UNAUTHORIZED.value()), "用户认证失败");
        // 处理异常结果
        String res = JSON.toJSONString(result);
        WebUtil.renderString(response, res);
    }
}
