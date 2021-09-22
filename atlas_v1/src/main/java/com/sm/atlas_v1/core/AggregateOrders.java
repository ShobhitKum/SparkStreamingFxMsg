package com.sm.atlas_v1.core;

import com.sm.atlas_v1.bean.FxMessage_bkp;

public interface AggregateOrders {

	public void aggregateByOrder(FxMessage_bkp message);
	public void aggregateByClosedOrder(FxMessage_bkp message);
	public void handleAmendOrder(FxMessage_bkp message);
	public void handleCancelledOrder(FxMessage_bkp message);

	
}
