package com.summer.oath.security.filter;

import com.summer.oath.security.config.JWTConfig;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT权限过滤器，用于验证Token是否合法
 *
 * @author yichuan
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /***
     *
     * filter 过滤请求是否包含authentication
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException
            , ServletException {
        log.info("doFilterInternal...");
        // 取出Token
        String token = request.getHeader(JWTConfig.tokenHeader);
        log.info("tokenHeader:{}", JWTConfig.tokenHeader);
        log.info("token:{}", token);
        if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
            SysUserDetails sysUserDetails = JWTTokenUtil.parseAccessToken(token);
            if (sysUserDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(sysUserDetails,
                        sysUserDetails.getUserId(), sysUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    /***
     *
     * @param request
     * @param response
     * @param authResult
     * @throws IOException
     */
    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        logger.info("身份验证成功");
        super.onSuccessfulAuthentication(request, response, authResult);
    }
}
