package com.chao.controller;

import com.chao.bean.ResponseMessage;
import com.chao.service.UserService;
import com.chao.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户信息前端验证
 */
@Controller
@RequestMapping(value = "/user/check")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/username")
    public ResponseMessage checkUsername(String username) {
        System.out.println(username);
        if (!CheckUtils.checkUsername(username)) {
            return ResponseMessage.fail().add("msg", "(S)用户名不符合规范!" + username);
        }
        if (userService.checkUsername(username)) {
            return ResponseMessage.fail().add("msg", "(S)用户名已存在!");
        }
        return ResponseMessage.success().add("msg", "(S)用户名可用！");
    }
}
