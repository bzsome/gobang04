package com.chao.server.channel;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelManager {
    private final static Logger logger = LoggerFactory.getLogger(ChannelManager.class);

    private static Map<String, Channel> userChannels = new HashMap<>();
    private static Map<String, Integer> userNetTypes = new HashMap();

    public static final int NET_TYPE_UNKNOWN = 0x00;
    public static final int NET_TYPE_WEB_SOCKET = 0x01;
    public static final int NET_TYPE_CHAT_CLIENT = 0x02;

    public static void add(String username, Channel channel, int netType) {
        logger.info("添加用户通道信息: {}, {}", username, netType);
        userChannels.put(username, channel);
        ChannelManager.setUserNetType(username, netType);
    }

    public static Channel getChannel(String username) {
        return userChannels.get(username);
    }

    public static String getUsername(Channel channel) {
        for (String key : userChannels.keySet()) {
            if (channel == userChannels.get(key)) {
                return key;
            }
        }
        return null;
    }

    public static void setUserNetType(String username, int netType) {
        userNetTypes.put(username, netType);
    }

    public static int getNetType(String username) {
        //如果在map中未找到value，将返回null，导致异常！
        if (userNetTypes.containsKey(username)) {
            return userNetTypes.get(username);
        } else {
            logger.warn("未找到用户通道类型！{}", username);
            return NET_TYPE_UNKNOWN;
        }
    }
}
