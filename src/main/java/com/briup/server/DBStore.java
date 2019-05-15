package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface DBStore extends WossModuleInit{
	public void storage(Collection<Environment> coll);
}
