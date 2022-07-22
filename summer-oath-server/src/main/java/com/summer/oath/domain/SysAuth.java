package com.summer.oath.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统权限
 */
@Data
@TableName("sys_auth")
public class SysAuth implements Serializable {

    /**
     * 权限ID
     */
    @TableId(value = "auth_id")
    private Integer authId;

    /**
     * 权限名称
     */
    @TableField(value = "auth_name")
    private String authName;

    /**
     * 权限标识
     */
    @TableField(value = "permission")
    private String permission;

}
