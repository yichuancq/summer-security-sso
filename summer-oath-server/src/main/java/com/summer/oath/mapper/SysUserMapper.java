package com.summer.oath.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.oath.domain.SysAuth;
import com.summer.oath.domain.SysRole;
import com.summer.oath.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户Dao
 *
 * @author yichuan
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

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
