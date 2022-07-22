package com.summer.oath.security.handler;

import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 *
 * @author yichuan
 */
@Component
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(sysUserDetails);
        // TODO: 2022/6/9  token 处理
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        log.info("登录成功");
        log.info("token: {}", token);
        ResultData resultData = new ResultData(ResultCode.NOT_LOGIN);
        Map<String, Object> resultMap = new HashMap<>();
        log.info("登录成功");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        resultData.setMessage("登录成功");
        resultData.setData(token);
        resultData.setResultMap(resultMap);
        response.getWriter().println(resultData);
        response.getWriter().flush();
    }
}
