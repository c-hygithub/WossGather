package com.briup.main;

import com.briup.server.ServerImpl;

public class ServerMain {
public static void main(String[] args) {
	ServerImpl serverImpl = new ServerImpl();
	try {
		serverImpl.acceptClient();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
}
