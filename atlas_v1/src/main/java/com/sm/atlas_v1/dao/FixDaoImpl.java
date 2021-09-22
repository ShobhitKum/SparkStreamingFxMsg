package com.sm.atlas_v1.dao;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

import org.apache.log4j.Logger;
import org.apache.spark.SparkEnv;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaDStream;

import com.sm.atlas_v1.bean.FxMessage_bkp;

public class FixDaoImpl implements IFxDao{
	final static Logger logger = Logger.getLogger(FixDaoImpl.class);
	
	public boolean save(JavaRDD<FxMessage_bkp> messageRDD) {
		try {
			javaFunctions(messageRDD).writerBuilder("sws_stage", "fxmessage", mapToRow(FxMessage_bkp.class)).saveToCassandra();
		}catch(Exception e) {
			logger.error("Exception occured while saving to db ",e);
			return false;
		}
		return true;
	}


	
	@Override
	public boolean save(JavaDStream<FxMessage_bkp> messageStream) {
		logger.info("ExecutorID save now to db--->"+SparkEnv.get().executorId());
		messageStream.foreachRDD(rdd ->  save(rdd));
		return true;
	}

}
