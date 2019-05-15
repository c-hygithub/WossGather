package com.briup.server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.briup.bean.Environment;

public class DBStoreImpl implements DBStore {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static {
	Properties properties = new Properties();
	try {
		properties.load(DBStoreImpl.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		user=properties.getProperty("user");
		password=properties.getProperty("password");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	}
	
	public void storage(Collection<Environment> coll){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName(driver);
			 conn = DriverManager.getConnection(url,user,password);
			String sql="insert into e_detail_1 values(?,?,?,?,?,?,?,?,?)";
			ps= conn.prepareStatement(sql);
			//获取coll里的environment进行赋值？号
			Iterator<Environment> iterator = coll.iterator();
			int count=0;
			while (iterator.hasNext()) {
				Environment environment = (Environment) iterator.next();
				count++;
				ps.setString(1,environment.getName());
				ps.setString(2,environment.getSendId());
				ps.setString(3,environment.getSmId());
				ps.setString(4,environment.getAddress());
				ps.setInt(5,environment.getCount());
				ps.setString(6,environment.getOrdernumber());
				ps.setInt(7,environment.getStatus());
				ps.setFloat(8,environment.getData());
				ps.setTimestamp(9,environment.getGather_date());
				ps.addBatch();
				if(count%1000==0) {
					ps.executeBatch();
					ps.clearBatch();
				}
				ps.addBatch();
				ps.executeBatch();
				/*
				 *  name varchar2(20),
				srcId varchar2(5),
				dstId varchar2(5),
				sersorAddress varchar2(7),
				count number(2),
				cmd  varchar2(5),
				status number(2),
				data number(9,4),
				gather_date date
				 */
				
			}
			conn.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		DBStoreImpl dbStoreImpl = new DBStoreImpl();
		ServerImpl serverImpl = new ServerImpl();
		Collection<Environment> coll;
		try {
			coll = serverImpl.acceptClient();
			dbStoreImpl.storage(coll);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void init(Properties properties) throws Exception {
	}
}
