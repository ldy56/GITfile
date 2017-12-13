package five.helloworld;

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
		
		
		//发送消息
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		
		//服务器端显示连续字符串：hello netty!!hello netty!!hello netty!!
		/*cf1.channel().write(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		cf1.channel().write(Unpooled.copiedBuffer("hello netty!!".getBytes()));
		cf1.channel().write(Unpooled.copiedBuffer("hello netty!!".getBytes()));*/

		//分隔发送消息
		//发送消息
		Thread.sleep(1000);  
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
		
		Thread.sleep(2000);
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
		
		cf1.channel().closeFuture().sync();
		
		group.shutdownGracefully();
		
	}
}
