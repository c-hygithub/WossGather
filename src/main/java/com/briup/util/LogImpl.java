package com.briup.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogImpl implements Log {
	private static Logger logger;
	public void debug(String message) {
		//获取logger的根节点
		Logger log = Logger.getRootLogger();
	//加载配置文件
		PropertyConfigurator.configure("log4j.properties");
	//编写配置文件
		//调用log4j方法就可以
	logger.debug(message);
		
	}
}
