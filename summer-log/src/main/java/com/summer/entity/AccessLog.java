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

    private String className; //类名

    private String methodName; //方法名

    private String operation; //操作内容

    private String params; //参数

    private String logLevel; //日志级别

    private String logMessage; //日志信息

    private String ip; //ip地址

    private Date createTime; //创建时间

    private Long time; //执行时长

    private String userName; //用户名
}
