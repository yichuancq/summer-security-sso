package com.summer.oath.service.impl;

import com.summer.oath.service.PassWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 密码服务类
 *
 * @author yichuan
 */
@Slf4j
@Service
public class PasswordServiceImpl implements PassWordService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean mach(String passWord, String EnPassWord) {
        boolean flag = bCryptPasswordEncoder.matches(passWord, EnPassWord);
        return flag;
    }
}
