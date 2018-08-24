package com.chao.service;

import com.chao.bean.UserBean;
import com.chao.bean.UserBeanExample;
import com.chao.dao.UserBeanMapper;
import com.chao.security.AuthorizationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserBeanMapper userBeanMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public UserBean login(String username, String password) {
        UserBeanExample userExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        String passMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        criteria.andPasswordEqualTo(passMD5);
        List<UserBean> users = userBeanMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /**
     * 通过用户名获得用户
     *
     * @param username
     * @return
     */
    public UserBean getUserByUsername(String username) {
        UserBeanExample userExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        if (userBeanMapper == null) {
            logger.debug("userBeanMapper 注入失败！");
            return null;
        }
        List<UserBean> users = userBeanMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /**
     * 更新用户信息(根据用户名)
     *
     * @param user
     */
    public boolean updateUserByUsername(UserBean user) {
        UserBeanExample userExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        int res = userBeanMapper.updateByExampleSelective(user, userExample);
        return res == 1;
    }

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    public boolean checkUsername(String username) {
        UserBeanExample userExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UserBean> users = userBeanMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 新增用户
     *
     * @param user
     */
    public boolean addUser(UserBean user) {
        if (user == null) {
            return false;
        }
        String passMD5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(passMD5);
        try {
            userBeanMapper.insertSelective(user);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
