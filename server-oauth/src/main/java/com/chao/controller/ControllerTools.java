package com.chao.controller;

import com.chao.bean.UserBean;
import com.chao.security.JWTUtil;
import com.chao.service.UserService;
import com.chao.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ControllerTools {

    @Autowired
    UserService userService;

    /**
     * 从header头中获得用户信息
     *
     * @param headers
     * @return
     */
    public  UserBean getUser(HttpHeaders headers) {
        String token = headers.getFirst(Constants.AUTHORIZATION);
        String username = JWTUtil.getUsername(token);
        return userService.getUserByUsername(username);
    }
}
