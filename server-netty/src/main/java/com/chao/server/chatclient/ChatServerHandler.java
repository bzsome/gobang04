package com.chao.server.chatclient;

import com.chao.domain.MyMessage;
import com.chao.server.channel.MessageManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端 channel
 *
 * @author waylau.com
 * @date 2015-2-16
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<MyMessage> { // (1)

    private final static Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        logger.info("加入：{}", ctx.channel().remoteAddress());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        logger.info("在线：{}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        logger.info("掉线：{}", ctx.channel().remoteAddress());
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

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) throws Exception {
        logger.info("收到消息：{}", myMessage.getMsg_text());
        MessageManager.handlerMessage(channelHandlerContext.channel(), myMessage);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.info("userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        logger.info("acceptInboundMessage");
        return super.acceptInboundMessage(msg);
    }
}