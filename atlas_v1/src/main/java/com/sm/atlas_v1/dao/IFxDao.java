package com.sm.atlas_v1.dao;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaDStream;

import com.sm.atlas_v1.bean.FxMessage_bkp;

public interface IFxDao {
	boolean save(JavaDStream<FxMessage_bkp> messageStream);

}
