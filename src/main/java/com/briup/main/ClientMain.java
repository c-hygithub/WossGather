package com.briup.main;

import java.util.ArrayList;
import java.util.List;

import com.briup.bean.Environment;
import com.briup.client.ClientImpl;
import com.briup.client.GatherImpl;

public class ClientMain {
	public static void main(String[] args) {
		//测试
		/*List<Environment> list= new ArrayList<Environment>();
		Environment environment = new Environment();
		environment.setName("hello");
		list.add(environment);*/
		ClientImpl clientImpl = new ClientImpl();
		try {
			clientImpl.sendServer(new GatherImpl().Read_File());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
