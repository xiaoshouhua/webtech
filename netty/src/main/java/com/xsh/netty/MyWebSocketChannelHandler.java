package com.xsh.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 3.初始化连接时候的各个组件
 * @author shouhua.xiao
 *
 */
public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("http-codec", new HttpServerCodec());
		ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
		ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		ch.pipeline().addLast("handler", new MyWebSocketHandler());
	}

}
