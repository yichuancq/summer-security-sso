package com.summer.oath.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author yichuan
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * ID
     */
    @TableId(value = "user_role_id")
    private Integer userRoleId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Integer roleId;

}
