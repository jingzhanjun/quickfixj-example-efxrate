package com.pactera.fix.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * QuoteRequest's properties
 */
public class QR implements Serializable {
    private static final long serialVersionUID = -9012229104347912713L;

    private String quoteReqID;
    private String partyID;
    private String symbol;
    /**1-b,2-s,7-two way*/
    private Character side;
    /**1.rfq,2.rfs*/
    private Integer executionStyle;
    /**0-SPOT,1-TODAY*/
    private String settlType;
    /**order amount*/
    private Double orderQty;
    private String account;
    private LocalDateTime transactTime;

    public String getQuoteReqID() {
        return quoteReqID;
    }

    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Character getSide() {
        return side;
    }

    public void setSide(Character side) {
        this.side = side;
    }

    public Integer getExecutionStyle() {
        return executionStyle;
    }

    public void setExecutionStyle(Integer executionStyle) {
        this.executionStyle = executionStyle;
    }

    public String getSettlType() {
        return settlType;
    }

    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDateTime getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(LocalDateTime transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public String toString() {
        return "QR{" +
                "quoteReqID='" + quoteReqID + '\'' +
                ", partyID='" + partyID + '\'' +
                ", symbol='" + symbol + '\'' +
                ", side=" + side +
                ", executionStyle=" + executionStyle +
                ", settlType='" + settlType + '\'' +
                ", orderQty=" + orderQty +
                ", account='" + account + '\'' +
                ", transactTime=" + transactTime +
                '}';
    }
}
