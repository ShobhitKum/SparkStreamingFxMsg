package com.sm.atlas_v1.transformer;

import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;

import com.sm.atlas_v1.bean.FxMessage_bkp;

public interface ITransformer {

	public JavaDStream<FxMessage_bkp> transform(JavaReceiverInputDStream<String> inputStream);
	
}
