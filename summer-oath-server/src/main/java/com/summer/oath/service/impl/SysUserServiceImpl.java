package com.summer.oath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.oath.domain.SysAuth;
import com.summer.oath.domain.SysRole;
import com.summer.oath.domain.SysUser;
import com.summer.oath.domain.vo.LoginResultVo;
import com.summer.oath.mapper.SysUserMapper;
import com.summer.oath.security.entity.SysUserDetails;
import com.summer.oath.service.PassWordService;
import com.summer.oath.service.SysUserService;
import com.summer.oath.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yichuan
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PassWordService passWordService;

    /**
     * 用户登录
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    public LoginResultVo login(String userName, String passWord) throws Exception {
        LoginResultVo loginResultVo = new LoginResultVo();
        if (userName == null || userName.isEmpty()) {
            throw new Exception("用户姓名为空");
        }
        if (passWord == null || passWord.isEmpty()) {
            throw new Exception("用户密码为空");
        }
        SysUser sysUser = findUserByUserName(userName);
        if (sysUser == null) {
            throw new Exception("查找用户失败");
        }
        boolean flag = passWordService.mach(passWord, sysUser.getPassword());
        if (!flag) {
            throw new Exception("用户密码不匹配");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        SysUserDetails sysUserDetails = new SysUserDetails();
        sysUserDetails.setUserId(sysUser.getUserId());
        //查询权限
        List<SysRole> roleList = this.findRoleByUserId(sysUser.getUserId());
        if (roleList != null && roleList.size() > 0) {
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });
        }
        //设置权限
        sysUserDetails.setAuthorities(authorities);
        //设置用户名称
        sysUserDetails.setUsername(sysUser.getUsername());
        sysUserDetails.setEnabled(true);
        sysUserDetails.setPassword(sysUser.getPassword());
        //证书是否过期
        sysUserDetails.setCredentialsNonExpired(false);
        //创建token
        String token = JWTTokenUtil.createAccessToken(sysUserDetails);
        sysUser.setToken(token);
        loginResultVo.setUserId(sysUser.getUserId());
        loginResultVo.setUserName(sysUser.getUsername());
        loginResultVo.setToken(sysUser.getToken());
        return loginResultVo;
    }


    /**
     * @param oldToken
     * @return
     * @throws Exception
     */
    public HashMap refreshToken(String oldToken, SysUserDetails sysUserDetails) throws Exception {
        String token = new JWTTokenUtil().refreshHeadToken(oldToken, sysUserDetails);
        HashMap hashMap = new HashMap();
        log.info("new token:{}", token);
        hashMap.put("token", token);
        return hashMap;

    }

    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username).ne(SysUser::getStatus,
                "1"));
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysRole> findRoleByUserId(Integer userId) {
        return this.baseMapper.findRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysAuth> findAuthByUserId(Integer userId) {
        return this.baseMapper.findAuthByUserId(userId);
    }

}
