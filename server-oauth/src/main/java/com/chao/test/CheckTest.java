package com.chao.test;

import com.chao.bean.UserBean;
import com.chao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class CheckTest {
    @Resource
    UserService userService;
    @Test
    public void check() {
        UserBean user = new UserBean();
        user.setUsername("admins");
        user.setPassword("123456a");

        System.out.println( userService.addUser(user));
    }
}
