package com.chao.server.websocket;

import com.chao.server.channel.ChannelManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单聊天服务器-服务端
 *
 * @author waylau.com
 * @date 2015-2-16
 */
public class WebSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(ChannelManager.class);
    private int port;

    public WebSocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap(); // (2)
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new WebSocketServerInitializer())  //(4)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            logger.info("WebSocketServer 启动了:{}", port);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = bootstrap.bind(port).sync(); // (7)

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            logger.info("WebSocketServer 关闭了:{}", port);
        }
    }

    public static void main(String[] args) throws Exception {
        new WebSocketServer(9901).run();

    }
}