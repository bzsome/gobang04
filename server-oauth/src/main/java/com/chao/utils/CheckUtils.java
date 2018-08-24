package com.chao.utils;

/**
 * 正则表达式检验
 */
public class CheckUtils {
    /**
     * 用户名检验
     *
     * @param username
     * @return
     */
    public static boolean checkUsername(String username) {
        //先判断用户名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        return username.matches(regx);
    }
}
