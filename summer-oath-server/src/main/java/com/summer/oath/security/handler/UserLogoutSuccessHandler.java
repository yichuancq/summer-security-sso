package com.summer.oath.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出成功处理类
 */
@Component
@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        log.info("登出成功");
        //ResponseUtils.responseJson(response, ResponseUtils.response(200, "登出成功", null));
    }
}
