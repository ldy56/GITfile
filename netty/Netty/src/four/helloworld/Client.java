package four.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static void main(String[] args) throws Exception{
		//创建一个线程组
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		//发起连接
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		//连接第二个服务端口
		ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
		
		//发送消息
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		//向第二个服务端口发送消息
		cf2.channel().writeAndFlush(Unpooled.copiedBuffer("hello world!!".getBytes()));

		cf1.channel().closeFuture().sync();
		cf2.channel().closeFuture().sync();
		
		group.shutdownGracefully();
		
	}
}
