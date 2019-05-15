package com.briup.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.server.DBStore;
import com.briup.server.Server;




public class ConfigurationImpl implements Configuration {
//解析配置文件config.xml
	
	//提供一个map，用来装产生的对象。
		private Map<String, WossModuleInit> map;
		//提供一个properties，用来初始化。
		private Properties properties;
	//解析工厂
	public ConfigurationImpl(){
		this("src/main/java/config.xml");
		
	}
	
	public ConfigurationImpl(String path)  {
		
		//获得一个SAXReader对象
		//document
		//根节点
		//产生下一个节点
		
				SAXReader reader = new SAXReader();
				File file = new File(path);
				//读取这个要解析的xml文件
				try {
					Document read = reader.read(file);
					Element rootElement = read.getRootElement();
					List<Element> elements = rootElement.elements();
					for (Element e1 : elements) {
						 String name = e1.attributeValue("class");
						 Object obj = Class.forName(name).newInstance();
						 if(obj instanceof WossModuleInit) {
							 map.put(e1.getName(), (WossModuleInit)obj);
						 
						 }
						 List<Element> elements2= e1.elements();
						 for (Element e2 : elements2) {
						properties.setProperty(e2.getName(), (String) e2.getData());
						}
						 if(obj instanceof WossModuleInit) {
							 ((WossModuleInit)obj).init(properties);
						 }
						 
					}
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				//获得document中的根节点
			}
	public Gather getGather() {
		return (Gather) map.get("Gather");
	}
	
	public Client getClient() {
		return (Client) map.get("Client");
	}
	public Server getServer() {
		return (Server) map.get("Server");
	}
	public DBStore getDBStore() {
		return (DBStore) map.get("DBStore");
	}
}
