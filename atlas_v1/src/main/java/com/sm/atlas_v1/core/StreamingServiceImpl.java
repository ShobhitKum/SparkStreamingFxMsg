package com.sm.atlas_v1.core;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import com.sm.atlas_v1.bean.FxMessage_bkp;
import com.sm.atlas_v1.cache.CacheImpl;
import com.sm.atlas_v1.cache.ICache;
import com.sm.atlas_v1.dao.FixDaoImpl;
import com.sm.atlas_v1.dao.IFxDao;
import com.sm.atlas_v1.transformer.ITransformer;
import com.sm.atlas_v1.transformer.MessageTransformer;
import com.sm.receiever.SocketReceiver;

import scala.Tuple2;

public class StreamingServiceImpl implements IStreamingService {
	final static Logger logger = Logger.getLogger(StreamingServiceImpl.class);

	private static final String HOST = "localhost";
	private static final int PORT = 8136;// before -6669
	//private static MessageTransformer transformer = new MessageTransformerImpl();
	private static String fixSplit = "\\^A ";
	private static String newLineSplit = "\\r?\\n";
	private static String keyvalueSplit = "=";
	//private static OrderProcessor orderProcessor = OrderProcessor.getInstance();
	static String CLUSTER_MODE = "spark://169.254.61.216:7077";
	private static String LOCAL_MODE = "local[2]";
	private static Object object = new Object();
	private static String redisPort="6379";
	//static MessageProcessor processor = null;
	ITransformer transformer=null;
	private SparkConf conf=null;
	private JavaStreamingContext streamingCtx=null;
	private IFxDao dao;
	private ICache cache = new CacheImpl();
	private AggregateOrders aggOrder = new AggregateOrderImpl();
	public void start() {
		init();
		transformer= new MessageTransformer();
		streamingCtx = getContext();
		com.sm.atlas_v1.util.Util util = new com.sm.atlas_v1.util.Util();
		JavaReceiverInputDStream<String> inputStream =streamingCtx.receiverStream(new SocketReceiver(StorageLevel.MEMORY_ONLY(),HOST,PORT));
		//JavaReceiverInputDStream<String> inputStream = streamingCtx.socketTextStream(HOST, PORT);
		try {
			JavaDStream<FxMessage_bkp> messageStream = transformer.transform(inputStream);
			//JavaDStream<FxMessage_bkp> filtered = messageStream.filter(fxMessage-> fxMessage.orderid!=null);
			logger.info("ExecutorID Kushob transformer & db--->"+SparkEnv.get().executorId());
			JavaDStream<FxMessage_bkp> filtered = util.filterMesage(messageStream, cache);
			JavaPairDStream<String, FxMessage_bkp> pair = messageStream.mapToPair(fm -> new Tuple2<>(fm.orderid, fm));
			JavaPairDStream<String, Long> count_order= pair.filter(fx->(fx._2.exectype.equals("2")||fx._2.exectype.equals("1"))).mapToPair(f -> new Tuple2<String,Long>(f._1,f._2.leavesqty)).reduceByKey((i1,i2)->i1+i2);
			//JavaPairDStream<String, Long> currencY= pair.filter(fx->(fx._2)

			pair.join(count_order);
			util.cacheOrder(messageStream, cache);
			dao.save(filtered);
			streamingCtx.start();
			streamingCtx.awaitTermination();
			streamingCtx.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void stop() {
		getContext().stop();
		
	}
	

	public void init() {
		try {
			if(conf==null){
				conf = new SparkConf().setMaster(CLUSTER_MODE).setAppName("atlascore_v1");
				conf.set("spark.cassandra.connection.host", "127.0.0.1").set("spark.executor.cores", "1").set("spark.executor.memory", "1g")
		        .set("spark.cassandra.connection.port", "9042");
				dao= new FixDaoImpl();
			}
		}catch(Exception e) {
			logger.error("Exception occurred in connecting to cassandra ",e);
			
		}
	
	}

	public JavaStreamingContext getContext() {
		JavaSparkContext sparkCtx = new JavaSparkContext(conf);
		JavaStreamingContext streamingContext = new JavaStreamingContext(sparkCtx, Durations.seconds(1));
		return streamingContext;
	}

}
