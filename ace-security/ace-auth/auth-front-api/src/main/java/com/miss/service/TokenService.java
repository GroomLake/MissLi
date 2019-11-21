package com.miss.service;

import com.miss.pojo.Token;

import java.util.List;

/**
 * @author MissLi
 * @create 2019/4/24 21:09
 */
public interface TokenService {
    List<Token> findAllToken();
    Token findTokenByUserName(String userName);
    boolean saveToken(Token token);
}
