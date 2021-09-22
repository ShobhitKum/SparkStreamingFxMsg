package com.sm.atlas_v1.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class FxMessage_bkp implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1575326291152986274L;
	public String orderid;
	public String messagetype;
	public String symbol;
	public String sendercompid;
	public String targetcompid;
	public long sendingtime;
	public String clorderid;
	public String execid;
	public String orderstatus;
	public String exectype;
	public String side;
	public long orderqty;
	public String ordertype;
	public BigDecimal price;
	public long lastqty;
	public long leavesqty;
	public String text;
	public long seqnum;
	public String origclid;

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSendercompid() {
		return sendercompid;
	}
	public void setSendercompid(String sendercompid) {
		this.sendercompid = sendercompid;
	}
	public String getTargetcompid() {
		return targetcompid;
	}
	public void setTargetcompid(String targetcompid) {
		this.targetcompid = targetcompid;
	}
	public long getSendingtime() {
		return sendingtime;
	}
	public void setSendingtime(long sendingtime) {
		this.sendingtime = sendingtime;
	}
	public String getClorderid() {
		return clorderid;
	}
	public void setClorderid(String clorderid) {
		this.clorderid = clorderid;
	}
	public String getExecid() {
		return execid;
	}
	public void setExecid(String execid) {
		this.execid = execid;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getExectype() {
		return exectype;
	}
	public void setExectype(String exectype) {
		this.exectype = exectype;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public long getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(long orderqty) {
		this.orderqty = orderqty;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getLastqty() {
		return lastqty;
	}
	public void setLastqty(long lastqty) {
		this.lastqty = lastqty;
	}
	public long getLeavesqty() {
		return leavesqty;
	}
	public void setLeavesqty(long leavesqty) {
		this.leavesqty = leavesqty;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getSeqnum() {
		return seqnum;
	}
	public void setSeqnum(long seqnum) {
		this.seqnum = seqnum;
	}
	public String getOrigclid() {
		return origclid;
	}
	public void setOrigclid(String origclid) {
		this.origclid = origclid;
	}
	@Override
	public String toString() {
		return "FxMessage [orderid=" + orderid + ", messagetype=" + messagetype + ", symbol=" + symbol
				+ ", sendercompid=" + sendercompid + ", targetcompid=" + targetcompid + ", sendingtime=" + sendingtime
				+ ", clorderid=" + clorderid + ", execid=" + execid + ", orderstatus=" + orderstatus + ", exectype="
				+ exectype + ", side=" + side + ", orderqty=" + orderqty + ", ordertype=" + ordertype + ", price="
				+ price + ", lastqty=" + lastqty + ", leavesqty=" + leavesqty + ", text=" + text + ", seqnum=" + seqnum
				+ ", origclid=" + origclid + "]";
	}


}
