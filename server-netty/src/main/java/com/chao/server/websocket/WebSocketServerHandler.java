package com.chao.server.websocket;

import com.chao.domain.MyMessage;
import com.chao.server.channel.ChannelManager;
import com.chao.server.channel.MessageManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端 channel
 *
 * @author waylau.com
 * @date 2015-2-16
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> { // (1)

    private final static Logger logger = LoggerFactory.getLogger(ChannelManager.class);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        logger.info("加入：{}", ctx.channel().remoteAddress());
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        logger.info("在线：{}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        MyMessage myMessage = MessageManager.getMessage(textWebSocketFrame.text());

        logger.info("收到消息：{}", myMessage.getMsg_text());
        MessageManager.handlerMessage(ctx.channel(), myMessage);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        logger.info("掉线：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("serEventTriggered");
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.info("异常：{}", ctx.channel().remoteAddress());
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("channelRead Object");
        super.channelRead(ctx, msg);
    }
}