package com.sm.atlas_v1.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.spark.SparkEnv;

import com.sm.atlas_v1.bean.FxMessage_bkp;
import com.sm.atlas_v1.util.AtlasConstants;
import com.sm.atlas_v1.util.CacheBuilder;

import redis.clients.jedis.Jedis;

public class CacheImpl implements ICache {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1727043233617725384L;
	final static Logger logger = Logger.getLogger(CacheImpl.class);
	//private Jedis_v2 cache;

	/*public CacheImpl() {
		cache = CacheBuilder.intGetCache();
	}
*/
/*	@Override
	public boolean add2OrderSet(String order) {
		Long val = cache.sadd(AtlasConstants.ORDERS, order);
		if(val==1){
			logger.info("Added to cache-"+order);
			return true;
		}
		logger.info("Found in cache processed order-"+order);
		return false;
	}

	@Override
	public boolean add2OrderStatusSet(String order, String status) {
		cache.hset(AtlasConstants.ORDER_STATUS, order, status);
		return true;
	}

	@Override
	public boolean push2OrderTrail(String index, String order, FxMessage_bkp message) {
		Map<String,String> map = new HashMap();
		map.put(order, message.toString());
		cache.hset(index, map);
		return true;
	}

	@Override
	public boolean updateTrailRefTable(String orderId, String index, FxMessage_bkp message) {
		cache.hset(orderId, index, message.toString());
		return true;
	}
*/
	@Override
	public boolean add2OrderSet(Jedis cache, FxMessage_bkp order , ArrayList<FxMessage_bkp> list) {
		Long val = cache.sadd(AtlasConstants.ORDERS, String.valueOf(order.seqnum));
		if(val==1){
			logger.info("Added to cache-"+order.orderid+" seq-"+order.seqnum+" executor->>"+SparkEnv.get().executorId());
			list.add(order);
			cache.rpush(AtlasConstants.PROCESSEDORDERS, String.valueOf(order.seqnum));
			return true;
		}else{
			cache.rpush(AtlasConstants.DUPSEQ, String.valueOf(order.seqnum));
		}
		logger.info("Found in cache processed order-"+order.orderid+" seq-"+order.seqnum +" executor->>"+SparkEnv.get().executorId());
		return false;
	}

	@Override
	public boolean aggregateOrder(Jedis cache, String json, String orderId) {
		String listKey = orderId+"_L";
		if(!cache.hexists(AtlasConstants.ORDER_TRAIL, orderId)){
			logger.info("Added to ORDER_TRAIL-"+orderId+" executor->>"+SparkEnv.get().executorId());

			cache.hset(AtlasConstants.ORDER_TRAIL, orderId,listKey);	
		}
		
		logger.info("Added to listKey-"+listKey+" val->"+json+" executor->>"+SparkEnv.get().executorId());

		cache.rpush(listKey, json);
		return true;
	}

	@Override
	public List<String> getOrders(Jedis cache, String orderId) {
		String listKey = orderId+"_L";
		List<String> orders = cache.lrange(listKey, 0, -1);
		return orders;
	}
	

	
	/*@Override
	public boolean updateTrailRefTable(String orderId, String index) {
		cache.hset(orderId, index, message.toString());
		return true;
	}*/

}
