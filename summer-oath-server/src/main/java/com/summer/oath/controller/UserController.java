package com.summer.oath.controller;

import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import com.summer.oath.domain.SysUser;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 普通用户UserController
 *
 * @author yichuan
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    /***
     *
     * @return
     */
    @GetMapping(path = "principal", value = "principal")
    @ApiOperation(notes = "登录获取token", value = "/principal")
    public ResultData<?> principal() {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            Object principal = authentication.getPrincipal();
            return new ResultData<>(ResultCode.SUCCESS, principal);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.info("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }

    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasPermission('/user/info', 'sys:user:info')")
    @GetMapping(value = "/showInfo")
    @ApiOperation(notes = "显示用户信息", value = "/showInfo")
    public ResultData<?> showInfo() {
        try {
            SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SysUser sysUser = sysUserService.getById(sysUserDetails.getUserId());
            log.info("sysUser:{}", sysUser);
            return new ResultData<>(ResultCode.SUCCESS, sysUser);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.info("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }
    }
}
