package com.summer.common.exception;

/**
 * 通用响应状态
 *
 * @author yichuan
 */
public enum ResultCode {
    /**
     * 成功状态码
     */
    SUCCESS(200, "请求成功！"), /* 错误状态码 */
    FAIL(-1, "操作失败！"), /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(1001, "参数无效"),//
    PARAM_IS_BLANK(1002, "参数为空"),//
    PARAM_TYPE_BIND_ERROR(1003, "参数格式错误"),//
    PARAM_NOT_COMPLETE(1004, "参数缺失"), /* 数据错误：50001-599999 */
    RESULT_DATA_NONE(5001, "数据未找到"), //
    DATA_IS_WRONG(5002, "数据有误"), //
    DATA_ALREADY_EXISTED(5003, "数据已存在"),
    NOT_LOGIN(4000, "用户未登录"),

    LOGIN_ERROR(4001, "用户名或密码错误"),

    UNKNOWN_ERROR(4002, "未知错误"),

    PARAMETER_ILLEGAL(4003, "参数不合法"),

    TOKEN_INVALID(4004, "无效的Token"),

    TOKEN_SIGNATURE_INVALID(4005, "无效的签名"),

    TOKEN_EXPIRED(4006, "token已过期"),

    TOKEN_MISSION(4007, "token缺失"),

    REFRESH_TOKEN_INVALID(4008, "刷新Token无效");

    /**
     * 操作代码
     */
    private int code;
    /**
     * 提示信息
     */
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
