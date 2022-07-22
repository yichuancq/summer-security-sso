package com.summer.oath.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.summer.oath.security.config.JWTConfig;
import com.summer.oath.security.entity.SysUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * JWT生产Token工具类
 */
@Slf4j
public class JWTTokenUtil {

    //失效标识
    private static final String CLAIM_KEY_CREATED = "exp";

    /**
     * 创建Token
     *
     * @param sysUserDetails 用户信息
     * @return
     */
    public static String createAccessToken(SysUserDetails sysUserDetails) {
        String token = Jwts.builder().setId(// 设置JWT
                        sysUserDetails.getUserId().toString()) // 用户Id
                .setSubject(sysUserDetails.getUsername()) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("summer") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret) // 签名算法、密钥
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities())).compact();// 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
        return JWTConfig.tokenPrefix + token;
    }

    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static SysUserDetails parseAccessToken(String token) {
        SysUserDetails sysUserDetails = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 去除JWT前缀
                token = token.substring(JWTConfig.tokenPrefix.length());
                // 解析Token
                Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();
                // 获取用户信息
                sysUserDetails = new SysUserDetails();
                sysUserDetails.setUserId(Integer.parseInt(claims.getId()));
                sysUserDetails.setUsername(claims.getSubject());
                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String authority = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority, new TypeReference<List<Map<String, String>>>() {
                    });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                sysUserDetails.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return sysUserDetails;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            // 从token中获取JWT中的负载
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(JWTConfig.secret)//密匙Key
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return claims;
    }

    /****
     * 当原来的token没过期时是可以刷新的
     * @param oldToken
     * @param sysUserDetails
     * @return
     */
    public String refreshHeadToken(String oldToken, SysUserDetails sysUserDetails) {
        if (StringUtils.isEmpty(oldToken)) {
            return null;
        }
        // 去除JWT前缀
        String token = oldToken.substring(JWTConfig.tokenPrefix.length());
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //token校验不通过
        Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();
        if (claims == null) {
            return null;
        }
        //如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            log.info("如果token没有过期，支持刷新");
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, 30 * 60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return createAccessToken(sysUserDetails);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        //取Claims的过期时间
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time))) {
            return true;
        }
        return false;
    }

}
