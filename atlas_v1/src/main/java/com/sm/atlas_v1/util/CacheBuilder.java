package com.sm.atlas_v1.util;

import java.io.Serializable;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class CacheBuilder implements Serializable{
	final static Logger logger = Logger.getLogger(CacheBuilder.class);

	private static final long serialVersionUID = 1L;
	static Jedis jcache;
	public static Jedis intGetCache(){
		try {
			if(jcache==null){
				synchronized(CacheBuilder.class){
					if(jcache==null)
						jcache = new Jedis();
					return jcache;
				}
			}
		}catch(Exception e) {
			logger.error("redis cache init failure ",e);
		}

		return jcache;
	}
	
}
