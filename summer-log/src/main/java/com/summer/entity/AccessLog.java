package com.summer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志记录类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog implements Serializable {
    private Long id;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 操作内容
     */
    private String operation;
    /**
     * 参数
     */
    private String params;
    /**
     * 日志级别
     */
    private String logLevel;
    /**
     * 日志信息
     */
    private String logMessage;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 执行时长
     */
    private Long time;
    /**
     * 用户名
     */
    private String userName;
}
