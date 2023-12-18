package org.codenotknock.secy.filter;

import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.codenotknock.secy.util.JWTUtil;
import org.codenotknock.secy.util.RedisCache;
import org.codenotknock.secy.vo.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author xiaofu
 */

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    OncePerRequestFilter 是 Spring Security 提供的一个抽象类，可以用于编写需要在每个 HTTP 请求中执行一次的过滤器。
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");

        if (StringUtils.isNullOrEmpty(token)) {
            // 放行 其他过滤器会判断认证的状态 没被认证
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String id;
        try {
            Claims claims = JWTUtil.getClaimsFromToken(token);
            id = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        // 从 redis 中获取用户信息
        String redisKey = "login" + id;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (null == loginUser) {
            throw new RuntimeException("用户未登录");
        }
        // TODO 获取权限信息 封装到 Authentication
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        // 存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
        /*   uper.setAuthenticated(true); 认证的操作
           public UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
                super(authorities);
                this.principal = principal;
                this.credentials = credentials;
                super.setAuthenticated(true);
            }

         */
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 认证后放行
        filterChain.doFilter(request, response);
    }
}
