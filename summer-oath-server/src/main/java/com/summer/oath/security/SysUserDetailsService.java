package com.summer.oath.security;

import com.summer.oath.domain.SysRole;
import com.summer.oath.domain.SysUser;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户登录Service
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if (sysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(sysUser, sysUserDetails);
            // 角色集合
            Set<GrantedAuthority> authorities = new HashSet<>();
            List<SysRole> roleList = sysUserService.findRoleByUserId(sysUserDetails.getUserId());
            if (roleList != null && roleList.size() > 0) {
                roleList.forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
                });
            }
            //设置权限
            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        return null;
    }

}
