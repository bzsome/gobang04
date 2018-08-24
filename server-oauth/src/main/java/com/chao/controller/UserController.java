package com.chao.controller;

import com.chao.bean.ResponseMessage;
import com.chao.bean.UserBean;
import com.chao.security.JWTUtil;
import com.chao.service.UserService;
import com.chao.utils.Constants;
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

    @Autowired
    UserService userService;
    @Autowired
    ControllerTools controllerTools;

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseMessage logout() {
        try {
            return ResponseMessage.success().add("logout", "退出成功！");
        } catch (Exception e) {
            return ResponseMessage.fail().add("logout", "退出出现错误！");
        }
    }

    /**
     * * 更新用户信息
     * <p>
     * 如果直接发送ajax=PUT形式的请求,获取不到数据
     * * 解决方案；
     * * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
     * * 1、配置上HttpPutFormContentFilter；
     * * 2、他的作用；将请求体中的数据解析包装成一个map。
     * <p>
     * * 此处需要权限，已过滤用户信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseMessage updateUser(UserBean user, @RequestHeader HttpHeaders headers) {
        UserBean olderUser = controllerTools.getUser(headers);
        user.setUsername(olderUser.getUsername());
        userService.updateUserByUsername(user);
        //从数据库中得到更新后的用户信息
        UserBean newUser = userService.getUserByUsername(user.getUsername());
        return new ResponseMessage().success().add("user", newUser);
    }
}
