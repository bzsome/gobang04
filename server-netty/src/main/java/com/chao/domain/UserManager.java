package com.chao.domain;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, String> users = new HashMap();

    static {
        users.put("chao", "123456");
    }

    public static void add(String username, String token) {
        users.put(username, token);
    }

    public static String getUser(String token) {
        for (String key : users.keySet()) {
            if (users.get(key).equals(token)) {
                return key;
            }
        }
        return null;
    }

}

