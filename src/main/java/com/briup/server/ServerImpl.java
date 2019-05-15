package com.briup.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.junit.experimental.runners.Enclosed;

import com.briup.bean.Environment;
import com.briup.client.GatherImpl;

public class ServerImpl implements Server{
	//发送list到客户端
	//建立连接
	
	public Collection<Environment> acceptClient() throws Exception {
		ServerSocket serverSocket = new ServerSocket(8899);
		
			Collection<Environment> conn=null;
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream ois= new ObjectInputStream(inputStream);
			conn= (Collection<Environment>) ois.readObject();
			 ois.close();
			 inputStream.close();
			 socket.close();
			 serverSocket.close();
			for (Environment environment : conn) {
				System.out.println(environment);
			
			//定义一个接收数据的collection
		}
			return conn;
	}

	public void init(Properties properties) throws Exception {
	}
		
}
