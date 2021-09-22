package com.sm.atlas_v1.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sm.atlas_v1.bean.FxMessage_bkp;

import redis.clients.jedis.Jedis;

//interface to add/update/delete cache
public interface ICache extends Serializable{

	//orderprocess cache
	public boolean add2OrderSet(Jedis cache, FxMessage_bkp order, ArrayList<FxMessage_bkp> list);
	//order status cache
	/*public boolean add2OrderStatusSet(String order, String status);
	//add to order trail 
	public boolean push2OrderTrail(String index, String order,FxMessage_bkp message);
	//order trail ref table;
	//public boolean updateTrailRefTable(String orderId,String index);
	boolean updateTrailRefTable(String orderId, String index, FxMessage_bkp message);*/
	
	public boolean aggregateOrder(Jedis cache, String json, String orderId);

	public List<String> getOrders(Jedis cache,String orderId);

	
	
	
	
}
