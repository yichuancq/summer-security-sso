package com.summer.oath.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author yichuan
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * token
     */
    @TableField(value = "token")
    private String token;

    /**
     * 状态（1-正常，0-禁用）
     */
    @TableField(value = "password")
    private String status;
}
