package com.summer.oath.service;

/***
 * 密码服务类
 * @author yichuan
 */
public interface PassWordService {

    /**
     * @param passWord
     * @param EnPassWord
     * @return
     */
    boolean mach(String passWord, String EnPassWord);
}
