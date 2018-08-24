package com.chao.server.channel;

import com.chao.domain.MyMessage;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageManager {
    private final static Logger logger = LoggerFactory.getLogger(MessageManager.class);

    /**
     * @param myMessage 向channel中发送消息
     */
    private static void sendToMessage(Channel channelReceive, MyMessage myMessage) {

        String receive_user = myMessage.getReceive_user();
        int netType = ChannelManager.getNetType(receive_user);
        logger.info("发送信息，接收者用户通道类型：{}", netType);

        switch (netType) {
            case ChannelManager.NET_TYPE_CHAT_CLIENT:
                channelReceive.writeAndFlush(myMessage);
                break;
            case ChannelManager.NET_TYPE_WEB_SOCKET:
                String json = new Gson().toJson(myMessage);
                channelReceive.writeAndFlush(new TextWebSocketFrame(json));
                break;
            case ChannelManager.NET_TYPE_UNKNOWN:
                logger.warn("未找到用户通道类型,{}", receive_user);
                break;
            default:
                logger.warn("通道类型错误,{}", netType);
        }
    }

    /**
     * 验证并处理Message信息
     *
     * @param channel
     * @param myMessage
     */
    public static void handlerMessage(Channel channel, MyMessage myMessage) {
        //找到发送者信息
        String send_user = ChannelManager.getUsername(channel);
        String receive_user = myMessage.getReceive_user();

        if (receive_user == null || receive_user.length() < 1) {
            logger.info("无法从channel中找到接收者信息！");
            return;
        }

        Channel channelReceive = ChannelManager.getChannel(receive_user);
        if (channelReceive == null || !channelReceive.isOpen()) {
            logger.info("接收者用户通道关闭：{}", receive_user);
            return;
        }

        logger.info("发送者：{}，接收者：{}", send_user, receive_user);
        //正确的发送message信息
        myMessage.setSend_user(send_user);
        sendToMessage(channelReceive, myMessage);
    }

    //将文本转换为对象
    public static MyMessage getMessage(String text) {
        return new Gson().fromJson(text, MyMessage.class);
    }
}
