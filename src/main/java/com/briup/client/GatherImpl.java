package com.briup.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.briup.bean.Environment;

/*
 * 获取收集到的文件进行读取和分割
 */
public class GatherImpl implements Gather{

	 public List<Environment> list=null;
	//读取文件；

	@SuppressWarnings("null")
	public List<Environment>  Read_File()  throws Exception{
		
		File file = new File("src/main/java/radwtmp");
		//File file = new File("E:/eclipseworkspace/WossGather/src/main/java/radwtmp");
		
		FileReader fr=new FileReader(file);
		BufferedReader br = new  BufferedReader(fr);
		String string=null;
		list=new ArrayList<Environment>();
		Environment environment = null;
		while ((string=br.readLine())!=null) {
			String[] split = string.split("[|]");
			
			//环境的名称有四种再环境的数值上有三种情况：光照256，二氧化碳1280，温湿度16；
			//当遇到温度湿度时候需要进行分割；
			/*
			 * 100|101|2|16|1|3|63d87a8002|1|1516329308291
			 *  0  1   2  3 4 5  6         7  8
 			电脑端ID 树莓派ID 板子模块ID 板子模块上具体的传感器ID 操控的传感器个数 
			发送的命令 温度湿度 状态 采集时间
			 */
			//放数据到environment
			//发送端id
			String sendId=split[0];
			//树莓派id
			String smId=split[1];
			//板子id
			String qmId=split[2];
			int count = Integer.parseInt(split[4]); //传感器的个数
			String ordernumber = split[5]; // 指令标号(3表示需要接受数据 16表示需要发送数据)
			int status = Integer.parseInt(split[7]); //状态码
			Timestamp gather_date = new Timestamp(Long.parseLong(split[8]));
			 String name=null;
			 float data = 0;
				
				if(split[3].equals("256")) {
					name="光照强度"; 
					data = Integer.valueOf(split[6].substring(0, 4), 16);
					//得到environment对象
					environment = new Environment(name, sendId, smId, qmId, split[3], count, ordernumber, status, data, gather_date);
					//装到集合里
					list.add(environment);
					
				}else if (split[3].equals("1280")) {
					name ="CO2浓度";
					data = Integer.valueOf(split[6].substring(0, 4), 16);
					//得到environment对象
					environment = new Environment(name, sendId, smId, qmId, split[3], count, ordernumber, status, data, gather_date);
					//装到集合里
					list.add(environment);
				}else {
					//继续去判断是温度还是湿度
					name ="温度";
					data=(float) ((Integer.parseInt(split[6].substring(0, 4), 16) * 0.00268127) - 46.85);
					//得到environment对象
					environment = new Environment(name, sendId, smId, qmId, split[3], count, ordernumber, status, data, gather_date);
					//装到集合里
					list.add(environment);
					
					name = "湿度";
					data= (float) ((Integer.parseInt(split[6].substring(4, 8), 16) * 0.00190735) - 6);
					//得到environment对象
					environment = new Environment(name, sendId, smId, qmId, split[3], count, ordernumber, status, data, gather_date);
				
					//装到集合里
					list.add(environment);
				}
				//count
			
			//Environment em=new Environment(name, SendId, SmId, QmId, address, count, ordernumber, status, data, gather_date);
			//list.add(em);
		}
			br.close();
	//读取一行数据字符流
	
	return list;
	
	}

	public void init(Properties properties) throws Exception {
	}
}
