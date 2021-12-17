package com.pactera.fix.domain;

import java.io.Serializable;

/**
 * NewOrderSingle's properties
 */
public class NOS implements Serializable {
    private static final long serialVersionUID = 4425601439954893388L;

    //common property
    private String partyID;
    private String quoteReqID;
    private String quoteID;
    private String clOrdID;
    /**1-b,2-s*/
    private Character side;
    private String account;
    /**userid*/
    private String issuer;
    /**gtmsId*/
    private String quoteRespID;
    /**price broker*/
    private String quoteMsgID;
    /**markup*/
    private Double spread;
    private String tradeDate;
    /**0-SPOT,1-TODAY*/
    private String settlType;
    /**1-rfq,2-rfs,3-one click*/
    private Integer executionStyle;
    private Double price;
    //one click fixed
    private String symbol;
    private Double orderQty;
    private Integer dps;
    private Double oneClickTolerance;
    /**1-FILL_AT_MY_RATE_ONLY,2-FILL_AT_LATEST,3-SLIPPAGE*/
    private Integer oneClickAction;
    private Double streamingQuote;

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getQuoteReqID() {
        return quoteReqID;
    }

    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public Character getSide() {
        return side;
    }

    public void setSide(Character side) {
        this.side = side;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getQuoteRespID() {
        return quoteRespID;
    }

    public void setQuoteRespID(String quoteRespID) {
        this.quoteRespID = quoteRespID;
    }

    public String getQuoteMsgID() {
        return quoteMsgID;
    }

    public void setQuoteMsgID(String quoteMsgID) {
        this.quoteMsgID = quoteMsgID;
    }

    public Double getSpread() {
        return spread;
    }

    public void setSpread(Double spread) {
        this.spread = spread;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettlType() {
        return settlType;
    }

    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    public Integer getExecutionStyle() {
        return executionStyle;
    }

    public void setExecutionStyle(Integer executionStyle) {
        this.executionStyle = executionStyle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getDps() {
        return dps;
    }

    public void setDps(Integer dps) {
        this.dps = dps;
    }

    public Double getOneClickTolerance() {
        return oneClickTolerance;
    }

    public void setOneClickTolerance(Double oneClickTolerance) {
        this.oneClickTolerance = oneClickTolerance;
    }

    public Integer getOneClickAction() {
        return oneClickAction;
    }

    public void setOneClickAction(Integer oneClickAction) {
        this.oneClickAction = oneClickAction;
    }

    public Double getStreamingQuote() {
        return streamingQuote;
    }

    public void setStreamingQuote(Double streamingQuote) {
        this.streamingQuote = streamingQuote;
    }
}
