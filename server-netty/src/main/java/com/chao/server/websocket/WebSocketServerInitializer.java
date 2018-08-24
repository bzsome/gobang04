package com.chao.server.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 服务端 ChannelInitializer
 *
 * @author waylau.com
 * @date 2015-2-26
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    /*n能够处理什么数据和顺序有关*/
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //能够处理http和websocket请求
        pipeline.addLast(new HttpServerCodec());    //将请求和应答消息编码或解码为HTTP消息
        pipeline.addLast(new HttpObjectAggregator(64 * 1024)); //将HTTP消息的多个部分组合成一条完整的HTTP消息
        pipeline.addLast(new ChunkedWriteHandler());

        //必须放在WebSocket配置之前
        pipeline.addLast(new HttpRequestHandler());

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new WebSocketServerHandler());

    }

}
