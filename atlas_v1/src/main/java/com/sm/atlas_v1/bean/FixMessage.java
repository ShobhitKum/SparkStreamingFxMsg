package com.sm.atlas_v1.bean;

public enum FixMessage {

	ORDERID(37),
	MESSAGETYPE(35),
	SYMBOL(55),
	SENDERCOMPID(49),
	TARGETCOMPID(56),
	SENDINGTIME(52),
	CLORDERID(11),
	EXECID(17),
	ORDERSTATUS(39),
	EXECTYPE(150),
	SIDE(54),
	ORDERQTY(38),
	ORDERTYPE(40),
	PRICE(44),
	LASTQTY(32),
	LEAVESQTY(151),
	TEXT(58),
	ORIGCLID(41),
	SEQNUM(7);
	
	private int filedNumber; 
	
	FixMessage(int fieldNumber){
		this.filedNumber = fieldNumber;
	}

	public static String getField(int num){
		for (FixMessage msg : FixMessage.values()){
			if(msg.filedNumber == num){
				//System.out.println("MsgName->"+msg.name().toString());
				return msg.name().toString();
			}
		}
		return null;
	}
	
	

}
