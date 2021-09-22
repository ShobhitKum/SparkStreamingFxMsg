package com.sm.atlas_v1.bean;

import java.io.Serializable;

public class FxMessage implements Serializable{
	public String ORDERID;
	public String MESSAGETYPE;
	public String SYMBOL;
	public String SENDERCOMPID;
	public String TARGETCOMPID;
	public String SENDINGTIME;
	public String CLORDERID;
	public String EXECID;
	public String ORDERSTATUS;
	public String EXECTYPE;
	public String SIDE;
	public String ORDERQTY;
	public String ORDERTYPE;
	public String PRICE;
	public String LASTQTY;
	public String LEAVESQTY;
	public String TEXT;
	public String SEQNUM;
	public String ORIGCLID;
	@Override
	public String toString() {
		return "Message [ORDERID=" + ORDERID + ", MESSAGETYPE=" + MESSAGETYPE + ", SYMBOL=" + SYMBOL + ", SENDERCOMPID="
				+ SENDERCOMPID + ", TARGETCOMPID=" + TARGETCOMPID + ", SENDINGTIME=" + SENDINGTIME + ", CLORDERID="
				+ CLORDERID + ", EXECID=" + EXECID + ", ORDERSTATUS=" + ORDERSTATUS + ", EXECTYPE=" + EXECTYPE
				+ ", SIDE=" + SIDE + ", ORDERQTY=" + ORDERQTY + ", ORDERTYPE=" + ORDERTYPE + ", PRICE=" + PRICE
				+ ", LASTQTY=" + LASTQTY + ", LEAVESQTY=" + LEAVESQTY + ", TEXT=" + TEXT + "]";
	}
	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
	public String getMESSAGETYPE() {
		return MESSAGETYPE;
	}
	public void setMESSAGETYPE(String mESSAGETYPE) {
		MESSAGETYPE = mESSAGETYPE;
	}
	public String getSYMBOL() {
		return SYMBOL;
	}
	public void setSYMBOL(String sYMBOL) {
		SYMBOL = sYMBOL;
	}
	public String getSENDERCOMPID() {
		return SENDERCOMPID;
	}
	public void setSENDERCOMPID(String sENDERCOMPID) {
		SENDERCOMPID = sENDERCOMPID;
	}
	public String getTARGETCOMPID() {
		return TARGETCOMPID;
	}
	public void setTARGETCOMPID(String tARGETCOMPID) {
		TARGETCOMPID = tARGETCOMPID;
	}
	public String getSENDINGTIME() {
		return SENDINGTIME;
	}
	public void setSENDINGTIME(String sENDINGTIME) {
		SENDINGTIME = sENDINGTIME;
	}
	public String getCLORDERID() {
		return CLORDERID;
	}
	public void setCLORDERID(String cLORDERID) {
		CLORDERID = cLORDERID;
	}
	public String getEXECID() {
		return EXECID;
	}
	public void setEXECID(String eXECID) {
		EXECID = eXECID;
	}
	public String getORDERSTATUS() {
		return ORDERSTATUS;
	}
	public void setORDERSTATUS(String oRDERSTATUS) {
		ORDERSTATUS = oRDERSTATUS;
	}
	public String getEXECTYPE() {
		return EXECTYPE;
	}
	public void setEXECTYPE(String eXECTYPE) {
		EXECTYPE = eXECTYPE;
	}
	public String getSIDE() {
		return SIDE;
	}
	public void setSIDE(String sIDE) {
		SIDE = sIDE;
	}
	public String getORDERQTY() {
		return ORDERQTY;
	}
	public void setORDERQTY(String oRDERQTY) {
		ORDERQTY = oRDERQTY;
	}
	public String getORDERTYPE() {
		return ORDERTYPE;
	}
	public void setORDERTYPE(String oRDERTYPE) {
		ORDERTYPE = oRDERTYPE;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getLASTQTY() {
		return LASTQTY;
	}
	public void setLASTQTY(String lASTQTY) {
		LASTQTY = lASTQTY;
	}
	public String getLEAVESQTY() {
		return LEAVESQTY;
	}
	public void setLEAVESQTY(String lEAVESQTY) {
		LEAVESQTY = lEAVESQTY;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	
	
	
	
	
	
}
