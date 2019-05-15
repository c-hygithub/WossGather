package com.briup.client;
/*
 * 客户这里展示从数据库里读取
 */

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface Client extends WossModuleInit{
	public void sendServer(Collection<Environment> conn) throws Exception;
}
