package com.xsh.jedis.client.alphal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class JRedis {

	private Socket socket;
	
	private InputStream inputStream;
	
	private OutputStream outputStream;
	
	public JRedis() {
	}
	
	public JRedis(String host,int port,boolean isLog4j){  
        try {  
            socket = new Socket();  
            //关闭socket时，立即释放socket绑定端口以便端口重用，默认为false  
            socket.setReuseAddress(true);  
            //关闭传输缓存，默认为false  
            socket.setTcpNoDelay(true);  
            //如果输入流等待1000毫秒还未获得服务端发送数据，则提示超时，0为永不超时  
            socket.setSoTimeout(10000);  
            //关闭socket时，底层socket不会直接关闭，会延迟一会，直到发送完所有数据  
            //等待10秒再关闭底层socket连接，0为立即关闭底层socket连接  
            socket.setSoLinger(true, 100);  
            //设置性能参数，可设置任意整数，数值越大，相应的参数重要性越高（连接时间，延迟，带宽）  
            socket.setPerformancePreferences(3, 2, 1);  
            SocketAddress address = new InetSocketAddress(host, port);  
            //socket创建超时时间为1000毫秒  
            socket.connect(address, 10000);  
              
            inputStream = socket.getInputStream();
    		outputStream = socket.getOutputStream();
            System.out.println("client ip:"+socket.getLocalAddress());  
            System.out.println("client port:"+socket.getLocalPort());  
            System.out.println("servetr ip:"+socket.getInetAddress());  
            System.out.println("servetr port:"+socket.getPort());  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.err.println("Cilent socket establish failed!");  
        }  
        System.out.println("Client socket establish success!");  
    }  

	public JRedis(String host,int port) throws IOException{
		socket = new Socket(host, port);
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}
	
	public String auth(String password) throws IOException{
		// 1.组装命令请求
		StringBuffer command = new StringBuffer();
		// 1.1请求由几部分数据组成
		command.append("*2").append("\r\n");
		// 1.2第一部分
		command.append("$4").append("\r\n");// 第一1部分数据的长度
		command.append("auth").append("\r\n");//第一部分的数据值
		//1.3第2部分
		command.append("$").append(password.getBytes().length).append("\r\n");//第2个部分数据的长度
		command.append(password).append("\r\n");//第2部分的数据值
		
		System.out.println("command:::"+command.toString());
		//2.发生命令到redis-server
		outputStream.write(command.toString().getBytes());

		// 3.获取redis的响应
		byte[] response = new byte[1024];
		
		outputStream.flush();
		
		inputStream.read(response);

		return new String(response);
	}
	
	public String set(String key,String value) throws IOException{
		// 1.组装命令请求
		StringBuffer command = new StringBuffer();
		// 1.1请求由几部分数据组成
		command.append("*3").append("\r\n");
		// 1.2第一部分
		command.append("$3").append("\r\n");// 第一1部分数据的长度
		command.append("set").append("\r\n");//第一部分的数据值
		//1.3第2部分
		command.append("$").append(key.getBytes().length).append("\r\n");//第2个部分数据的长度
		command.append(key).append("\r\n");//第2部分的数据值
		//1.4第3部分value
		command.append("$").append(value.getBytes().length).append("\r\n");//第3个部分数据的长度
		command.append(value).append("\r\n");//第3部分的数生居值
		
		System.out.println("command:::"+command.toString());
		//2.发生命令到redis-server
		outputStream.write(command.toString().getBytes());
		// 3.获取redis的响应
		byte[] response = new byte[1024];
		inputStream.read(response);
		return new String(response);
	}
	
	public String del(String key) throws IOException{
		// 1.组装命令请求
		StringBuffer command = new StringBuffer();
		// 1.1请求由几部分数据组成
		command.append("*2").append("\r\n");
		// 1.2第一部分
		command.append("$3").append("\r\n");// 第一1部分数据的长度
		command.append("del").append("\r\n");//第一部分的数据值
		//1.3第2部分
		command.append("$").append(key.getBytes().length).append("\r\n");//第2个部分数据的长度
		command.append(key).append("\r\n");//第2部分的数据值
		
		System.out.println("command:::"+command.toString());
		//2.发生命令到redis-server
		outputStream.write(command.toString().getBytes());
		// 3.获取redis的响应
		byte[] response = new byte[1024];
		inputStream.read(response);
		return new String(response);
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		JRedis jRedis = new JRedis("172.16.21.205",6379);
		System.out.println("auth:::"+jRedis.auth("fcpwd1305"));;
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 1000; i++) {
//			System.out.println("remove:::"+jRedis.del("jack_"+i));;
		}
		
		jRedis.close();
		long endTime = System.currentTimeMillis();
		System.out.println("耗时："+(endTime-startTime)+"ms");
	}
}
