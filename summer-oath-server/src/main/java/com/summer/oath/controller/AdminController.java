package com.summer.oath.controller;

import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import com.summer.oath.domain.SysUser;
import com.summer.oath.security.config.JWTConfig;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yichuan
 */
@RestController
@RequestMapping(value = "/admin")
@Slf4j
@Api(tags = "管理员管理")
public class AdminController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @param userName
     * @return
     */
    @GetMapping(value = "/login")
    @ApiOperation(notes = "用户登录", value = "/login")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "用户密码", required = true, paramType = "query", dataType = "String")})
    public ResultData<?> login(@Param("userName") String userName, @Param("passWord") String passWord) {
        try {
            log.info("用户登录");
            log.info("userName:{}", userName);
            log.info("passWord:{}", passWord);
            //http://localhost:9010/admin/login?userName=yichuan&passWord=123456
            return new ResultData<>(ResultCode.SUCCESS, sysUserService.login(userName, passWord));
        } catch (Exception exception) {
            log.error("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }
    }

    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "/refreshToken", notes = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> refreshToken(HttpServletRequest request) {
        try {
            log.info("刷新token...");
            //tokenHeader
            String token = request.getHeader(JWTConfig.tokenHeader);
            SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResultData<>(ResultCode.SUCCESS, sysUserService.refreshToken(token, sysUserDetails));
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/list")
    public ResultData<?> list() {
        try {
            List<SysUser> userList = sysUserService.list();
            return new ResultData(ResultCode.SUCCESS, userList);
        } catch (Exception exception) {
            log.error("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }
    }

}


