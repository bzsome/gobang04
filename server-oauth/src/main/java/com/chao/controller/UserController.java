package com.chao.controller;

import com.chao.bean.ResponseMessage;
import com.chao.bean.UserBean;
import com.chao.security.JWTUtil;
import com.chao.service.UserService;
import com.chao.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 用户登录控制
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    ControllerTools controllerTools;

    @ResponseBody
    @GetMapping(value = "/user")
    public ResponseMessage getUser(@RequestHeader HttpHeaders headers) {
        UserBean user = controllerTools.getUser(headers);
        return ResponseMessage.success().add("user", user);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseMessage userLogin(String username, String password) {
        UserBean userBean = userService.login(username, password);
        if (userBean == null) {
            return ResponseMessage.fail();
        }
        String token = JWTUtil.sign(username, password);
        return ResponseMessage.success().add("token", token).add("user", userBean);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/register")
    public ResponseMessage userRegister(UserBean user) {
        boolean is = userService.addUser(user);
        if (is) {
            String token = JWTUtil.sign(user.getUsername(), user.getPassword());
            return ResponseMessage.success().add("token", token);
        }
        return ResponseMessage.fail().add("error", "注册失败，无法写入数据库！");
    }

    /**
     * 退出
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/logout")
    public ResponseMessage logout() {
        try {
            return ResponseMessage.success().add("logout", "退出成功！");
        } catch (Exception e) {
            return ResponseMessage.fail().add("logout", "退出出现错误！");
        }
    }

    /**
     * * 更新用户信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/user")
    public ResponseMessage updateUser(UserBean user, @RequestHeader HttpHeaders headers) {
        logger.info("更新用户信息：{}", user.toString());
        UserBean olderUser = controllerTools.getUser(headers);
        user.setUid(olderUser.getUid());
        userService.updateUser(user);
        //从数据库中得到更新后的用户信息
        UserBean newUser = userService.getUserByUsername(olderUser.getUsername());
        return new ResponseMessage().success().add("user", newUser);
    }
}
