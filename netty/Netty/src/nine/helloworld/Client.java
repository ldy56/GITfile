package nine.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

public class Client {

	
	public static void main(String[] args) throws Exception{
		
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group)
		 .channel(NioSocketChannel.class)
		 .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//设置序列化框架
				sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
				sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
				sc.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
		
		//将5个对象冲刷到服务器端
		for(int i = 0; i < 5; i++ ){
			Req req = new Req();
			req.setId("" + i);
			req.setName("pro" + i);
			req.setRequestMessage("数据信息" + i);	
			
			//读指定的文件
			String path = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "001.jpg";
			File file = new File(path);
	        FileInputStream in = new FileInputStream(file);  
	        byte[] data = new byte[in.available()];  
	        in.read(data);  
	        in.close(); 
			
			req.setAttachment(GzipUtils.gzip(data));  //压缩发送的文件

			cf.channel().writeAndFlush(req);
		}

		cf.channel().closeFuture().sync();
		group.shutdownGracefully();
	}
}
