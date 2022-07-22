package com.summer.oath.security;

import com.summer.oath.domain.SysAuth;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限注解处理类
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 判断是否拥有权限
     *
     * @param authentication 用户身份
     * @param targetUrl      目标路径
     * @param permission     路径权限
     * @return 是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        //用户权限
        Set<String> permissions = new HashSet<String>();
        //
        List<SysAuth> authList = sysUserService.findAuthByUserId(sysUserDetails.getUserId());
        authList.forEach(auth -> {
            permissions.add(auth.getPermission());
        });
        //判断是否拥有权限
        if (permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
