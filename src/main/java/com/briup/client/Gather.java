package com.briup.client;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

/*
 * 收集信息
 */
public interface Gather extends WossModuleInit {
	public List<Environment>  Read_File() throws Exception;
}
