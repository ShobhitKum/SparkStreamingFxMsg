package com.sm.atlas_v1.transformer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;

import com.sm.atlas_v1.bean.FixMessage;
import com.sm.atlas_v1.bean.FxMessage_bkp;
import com.sm.atlas_v1.dao.FixDaoImpl;
import com.sm.atlas_v1.dao.IFxDao;

import scala.Tuple2;

public class MessageTransformer implements ITransformer {
	private static String fixSplit = "\\^A ";
	private static String newLineSplit = "\\r?\\n";
	private static String keyvalueSplit = "=";
	IFxDao dao;

	
	public MessageTransformer(){
		dao = new FixDaoImpl();
	}
	public static JavaPairRDD<String, FxMessage_bkp> getKVRDD(JavaRDD<String[]> accessLogRDD) {
		return accessLogRDD.map(x -> populateMessage(x)).mapToPair(log -> new Tuple2<>(log.orderid, log));
	}

	public static JavaRDD<String[]> getFlatRDD(JavaRDD<List<String[]>> accessLogRDD) {
		return accessLogRDD.flatMap(ev -> ev.iterator());
	}

	public static JavaRDD<FxMessage_bkp> getMessageRDD(JavaRDD<String[]> accessLogRDD) {
		return accessLogRDD.map(x -> populateMessage(x));
	}

	public static void populate(String[] key, List<FxMessage_bkp> list) {
		FxMessage_bkp message = new FxMessage_bkp();
		Arrays.stream(key).map(x2 -> x2.split(keyvalueSplit)).forEach(sp -> populate(sp[0], sp[1], message));
		// System.out.println("yoo--" + message);
		// processor.put(message);
		list.add(message);
	}

	public static FxMessage_bkp populateMessage(String[] key) {
		FxMessage_bkp message = new FxMessage_bkp();
		Arrays.stream(key).map(x2 -> x2.split(keyvalueSplit)).forEach(sp -> populate(sp[0], sp[1], message));
		// System.out.println("yoo--" + message);
		// processor.put(message);
		return message;
	}

	public static FxMessage_bkp populate(String key, String value, FxMessage_bkp msg) {
		String field = FixMessage.getField(Integer.valueOf(key));
		// System.out.println("Field-->"+field+" key-"+Integer.valueOf(key));
		if (field != null) {
			if (field.equals("ORDERID")) {
				msg.orderid = value;
			} else if (field.equals("MESSAGETYPE")) {
				msg.messagetype = value;
			} else if (field.equals("SYMBOL")) {
				msg.symbol = value;
			} else if (field.equals("SENDERCOMPID")) {
				msg.sendercompid = value;
			} else if (field.equals("TARGETCOMPID")) {
				msg.targetcompid = value;
			} else if (field.equals("SENDINGTIME")) {
				msg.sendingtime = Long.valueOf(value);
			} else if (field.equals("CLORDERID")) {
				msg.clorderid = value;
			} else if (field.equals("EXECID")) {
				msg.execid = value;
			} else if (field.equals("ORDERSTATUS")) {
				msg.orderstatus = value;
			} else if (field.equals("EXECTYPE")) {
				msg.exectype = value;
			} else if (field.equals("SIDE")) {
				msg.side = value;
			} else if (field.equals("ORDERQTY")) {
				msg.orderqty = Long.valueOf(value);
			} else if (field.equals("ORDERTYPE")) {
				msg.ordertype = value;
			} else if (field.equals("PRICE")) {
				msg.price = new BigDecimal(value);
			} else if (field.equals("LASTQTY")) {
				msg.lastqty = Long.valueOf(value);
			} else if (field.equals("LEAVESQTY")) {
				msg.leavesqty = Long.valueOf(value);
			} else if (field.equals("SEQNUM")) {
				msg.seqnum = Long.valueOf(value);
			} else if (field.equals("ORIGCLID")) {
				msg.origclid = value;
			} else if (field.equals("TEXT")) {
				msg.text = value;
			} else if (field.equals("SEQNUM")) {
				msg.seqnum = Long.valueOf(value);
			}

		}
		return msg;
	}

	@Override
	public JavaDStream<FxMessage_bkp> transform(JavaReceiverInputDStream<String> inputStream) {
		JavaDStream<List<String[]>> x = inputStream.map(line -> line.split(newLineSplit)).map(
				newLine -> Arrays.stream(newLine).map(everyL -> everyL.split(fixSplit)).collect(Collectors.toList()));

		JavaDStream<FxMessage_bkp> dS = x.transform(MessageTransformer::getFlatRDD)
				.transform(MessageTransformer::getMessageRDD);
		return dS;
	}

}
