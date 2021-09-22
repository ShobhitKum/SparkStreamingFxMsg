package com.sm.atlas_v1.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.spark.SparkEnv;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;

import com.google.gson.Gson;
import com.sm.atlas_v1.bean.FxMessage_bkp;
import com.sm.atlas_v1.cache.ICache;
import com.sm.atlas_v1.core.StreamingServiceImpl;

import redis.clients.jedis.Jedis;
import scala.Tuple2;

public class Util implements Serializable {

	private static final long serialVersionUID = 8769371000692478559L;
	final static Logger logger = Logger.getLogger(StreamingServiceImpl.class);

	public JavaDStream<FxMessage_bkp> filterMesage(JavaDStream<FxMessage_bkp> messageStream, ICache cache){
		return messageStream.mapPartitions(fm -> {ArrayList<FxMessage_bkp> list = new ArrayList();
		Jedis jcache=CacheBuilder.intGetCache();
		if(null!=jcache) {
			fm.forEachRemaining(message -> { cache.add2OrderSet(jcache,message,list);});
		}else {
			logger.info("redis cache failure please cehck logs");
		}
		
		return list.iterator();
		});
	}
	
	
	public void cacheOrder(JavaDStream<FxMessage_bkp> messageStream,ICache cache){
		
		JavaPairDStream<String, String> pair = messageStream.mapToPair(fm -> new Tuple2<>(fm.orderid, getGsonObject().toJson(fm)));
		pair.foreachRDD(rdd -> rdd.foreach(tu-> cache.aggregateOrder(CacheBuilder.intGetCache(), tu._2, tu._1)));
		logger.info("ExecutorID writing to db--->"+SparkEnv.get().executorId());
		
	}
	
	public Gson getGsonObject(){
		Gson gson = new Gson();
		return gson;
	}
	
}
