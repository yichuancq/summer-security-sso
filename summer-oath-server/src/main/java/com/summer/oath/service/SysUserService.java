package com.summer.oath.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.oath.domain.SysAuth;
import com.summer.oath.domain.SysRole;
import com.summer.oath.domain.SysUser;
import com.summer.oath.domain.vo.LoginResultVo;
import com.summer.oath.security.entity.SysUserDetails;

import java.util.HashMap;
import java.util.List;

/**
 * 系统用户Service
 *
 * @author yichuan
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 用户登录
     *
     * @param userName
     * @return
     * @throws Exception
     */
    LoginResultVo login(String userName, String passWord) throws Exception;

    /**
     * @param oldToken
     * @param sysUserDetails
     * @return
     * @throws Exception
     */
    HashMap refreshToken(String oldToken, SysUserDetails sysUserDetails) throws Exception;

    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    SysUser findUserByUserName(String username);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> findRoleByUserId(Integer userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    List<SysAuth> findAuthByUserId(Integer userId);

}