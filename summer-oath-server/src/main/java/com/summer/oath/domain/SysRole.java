package com.summer.oath.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统角色
 *
 * @author yichuan
 */
@Data
@TableName("sys_role")
@Setter
@Getter
public class SysRole implements Serializable {
    /**
     * 角色ID
     */
    @TableId(value = "role_id")
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

}
