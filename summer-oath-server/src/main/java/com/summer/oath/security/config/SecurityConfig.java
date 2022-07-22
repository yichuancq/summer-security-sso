package com.summer.oath.security.config;

import com.summer.oath.security.UserAuthenticationProvider;
import com.summer.oath.security.UserPermissionEvaluator;
import com.summer.oath.security.filter.JWTAuthenticationFilter;
import com.summer.oath.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


/**
 * 系统安全核心配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 无权限处理类
     */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 用户登录验证
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 用户权限注解
     */
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }


    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内置账号
        //auth.inMemoryAuthentication().withUser("yichuan").password("123456").roles("admin");
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /*** 安全权限配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置Swagger2匿名访问
//                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()// 获取白名单（不进行权限验证）
                .antMatchers("/admin/**").permitAll()//admin 的api请求 放入白名单
                .antMatchers("/doc.html").permitAll()//knife4j 放入白名单
                .antMatchers("/doc.html#/home").permitAll()//
                .antMatchers("/swagger-ui.html").permitAll()//
                .antMatchers("/webjars/**").permitAll()//
                .antMatchers("/swagger-resources/**").permitAll()//
                .antMatchers("/v2/*").permitAll()//
                .antMatchers("/csrf").permitAll()//
                .antMatchers("/").permitAll()//
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()// 获取白名单（不进行权限验证）
                .anyRequest().authenticated() // 其他的需要登陆后才能访问
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler) // 配置未登录处理类
                .and().formLogin().loginProcessingUrl("/login/submit")// 配置登录URL
                .successHandler(userLoginSuccessHandler) // 配置登录成功处理类
                .failureHandler(userLoginFailureHandler) // 配置登录失败处理类
                .and().logout().logoutUrl("/logout/submit")// 配置登出地址
                .logoutSuccessHandler(userLogoutSuccessHandler) // 配置用户登出处理类
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)// 配置没有权限处理类
                .and().cors()// 开启跨域
                .and().csrf().disable(); // 禁用跨站请求伪造防护
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session（使用Token认证）
        http.headers().cacheControl(); // 禁用缓存
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));

    }

}