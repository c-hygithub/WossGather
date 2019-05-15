package com.briup.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;

public class ClientImpl implements Client {

	//连接服务器端
	public void sendServer(Collection<Environment> conn) throws Exception{
		
		Socket socket = new Socket("192.168.224.1",8899);
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream);
		oos.writeObject(conn);
		oos.flush();
		oos.close();
		socket.close();
		
	}

	public void init(Properties properties) throws Exception {
	}
}
