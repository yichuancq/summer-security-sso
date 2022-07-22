package com.summer.oath.controller;

import com.summer.common.exception.ResultCode;
import com.summer.common.response.ResultData;
import com.summer.oath.domain.SysUser;
import com.summer.oath.domain.SysUserRole;
import com.summer.oath.service.SysUserRoleService;
import com.summer.oath.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册用户实例Controller
 *
 * @author yichuan
 */
@RestController
@Slf4j
@RequestMapping(value = "/register")
@Api(tags = "用户注册管理")
public class RegisterController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 注册普通用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping(value = "/addUser")
    @ApiOperation(notes = "注册用户", value = "/addUser")
    public ResultData<?> addUser(String username, String password) {

        try {
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            //密码加密
            sysUser.setPassword(bCryptPasswordEncoder.encode(password));
            sysUser.setStatus("1");
            sysUserService.save(sysUser);
            // 注册普通用户
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(2);
            sysUserRoleService.save(sysUserRole);
            return new ResultData<>(ResultCode.SUCCESS, sysUser);
        } catch (Exception exception) {
            exception.printStackTrace();
            log.info("error:{}", exception.getMessage());
            return new ResultData<>(ResultCode.FAIL, exception.getMessage());
        }
    }
}
