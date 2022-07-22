package com.summer.oath.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关系
 */
@Data
@TableName("sys_role_auth")
public class SysRoleAuth implements Serializable {
    /**
     * ID
     */
    @TableId(value = "role_auth_id")
    private Integer roleAuthId;
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 权限ID
     */
    @TableField(value = "auth_id")
    private Integer authId;

}
