package com.summer.oath.security.handler;

import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 未登录处理类
 *
 * @author yichuan
 */
@Component
@Slf4j
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    /****
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultData resultData = new ResultData(ResultCode.NOT_LOGIN);
        Map<String, Object> resultMap = new HashMap<>();
        log.info("未登录");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        resultData.setMessage("未登录");
        resultMap.put("msg", authException.getMessage());
        resultData.setResultMap(resultMap);
        response.getWriter().println(resultData);
        response.getWriter().flush();
    }
}
