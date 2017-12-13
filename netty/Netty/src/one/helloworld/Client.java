package one.helloworld;

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
		//发起连接，指定连接服务器端的端口号
		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
		
		//发送消息
		cf1.channel().write(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		cf1.channel().flush();
		
		cf1.channel().closeFuture().sync();
		
		//释放程序组
		group.shutdownGracefully();
		
	}
}
