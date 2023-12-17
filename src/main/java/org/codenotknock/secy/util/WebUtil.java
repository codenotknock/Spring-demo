package org.codenotknock.secy.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaofu
 */
public class WebUtil {
    /**
     * @des 将字符串渲染到客户端
     * @param response string
     * @return
     */



    public static String renderString(HttpServletResponse response ,String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
