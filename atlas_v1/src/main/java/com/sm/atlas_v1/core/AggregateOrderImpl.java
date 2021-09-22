package com.sm.atlas_v1.core;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.sm.atlas_v1.bean.FxMessage_bkp;

public class AggregateOrderImpl implements AggregateOrders {

	ConcurrentHashMap<String,List<FxMessage_bkp>> aggreagteCache = new ConcurrentHashMap<String,List<FxMessage_bkp>>();
	ConcurrentHashMap<String,FxMessage_bkp> closedOrderCache = new ConcurrentHashMap<String,FxMessage_bkp>();
	ConcurrentHashMap<String,FxMessage_bkp> amendCache = new ConcurrentHashMap<String,FxMessage_bkp>();
	
	
	@Override
	public void aggregateByOrder(FxMessage_bkp message) {
		String orderId = message.orderid;
		if(aggreagteCache.contains(orderId)){
			aggreagteCache.get(orderId).add(message);
		}
	}

	@Override
	public void aggregateByClosedOrder(FxMessage_bkp message) {
		String orderId = message.orderid;
		closedOrderCache.put(orderId, message);
	}

	@Override
	public void handleAmendOrder(FxMessage_bkp message) {
		String orderId = message.orderid;
		amendCache.put(orderId, message);
	}

	@Override
	public void handleCancelledOrder(FxMessage_bkp message) {
		String orderId = message.orderid;
		closedOrderCache.put(orderId, message);
	}
	
	

}
