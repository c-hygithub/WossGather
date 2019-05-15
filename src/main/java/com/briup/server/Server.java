package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface Server extends WossModuleInit{
	public Collection<Environment> acceptClient() throws Exception ;
}
