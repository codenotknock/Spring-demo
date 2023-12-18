package org.codenotknock.secy.config;


import org.codenotknock.secy.filter.JwtAuthenticationTokenFilter;
import org.codenotknock.secy.handle.MyFailHandler;
import org.codenotknock.secy.handle.MyLogoutSuccessHandler;
import org.codenotknock.secy.handle.MySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author xiaofu
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 开启授权 prePostEnabled 注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyFailHandler myFailHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {

        // 加密方法
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 用户认证 注入spring中
        return super.authenticationManagerBean();
    }


//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            // 所有接口都需要认证被访问
//            http.authorizeRequests().anyRequest().authenticated();
//
//            http.formLogin().successHandler(mySuccessHandler)
//                            .failureHandler(myFailHandler);
//
//           // 注销成功处理
//            http.logout().logoutSuccessHandler(myLogoutSuccessHandler);
//        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    // 关闭 csrf
                .csrf().disable()
                // 不通过 Session 获取 SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 登录接口，匿名访问（匿名访问是未登录可访问，登录后不可被访问）
                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                // 学习：从配置中设置权限
                .antMatchers("/hello").hasAnyAuthority("sys:dept:list", "sys:dept:hello")
                .antMatchers("/test").hasAuthority("sys:dept:hello")
                // 除上面之外的所有请求都需要鉴权认证
                .anyRequest().authenticated();

        // jwt 验证在身份认证过滤器 UsernamePasswordAuthenticationFilter 的前面
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器（认证、授权）
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();
    }
}
