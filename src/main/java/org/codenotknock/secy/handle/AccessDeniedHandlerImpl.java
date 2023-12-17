package org.codenotknock.secy.handle;

import com.alibaba.fastjson.JSON;
import org.codenotknock.secy.ResponseResult;
import org.codenotknock.secy.util.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaofu
 * 授权失败的处理：统一 json 格式返回给前端
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
 /*
    如果是授权过程中出现的异常会被封装成AccessDeniedException
        然后调用 AccessDeniedHandler 对象的 handle 方法去进行异常处理。
 */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.fail(null, String.valueOf(HttpStatus.FORBIDDEN.value()), "用户没有权限");
        // 处理异常结果
        String res = JSON.toJSONString(result);
        WebUtil.renderString(response, res);
    }

}
