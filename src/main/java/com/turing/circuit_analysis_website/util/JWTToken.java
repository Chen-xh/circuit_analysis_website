package com.turing.circuit_analysis_website.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author CHEN
 * @date 2020/3/1 16:45
 * AuthenticationToken: shiro中负责把username,password生成用于验证的token的封装类
 * 我们需要自定义一个对象用来包装token。
 */
public class JWTToken implements AuthenticationToken {

    private String token;


    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}