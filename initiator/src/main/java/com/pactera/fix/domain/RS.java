package com.pactera.fix.domain;

import java.io.Serializable;

/**
 * MarketDataRequest.NoRelatedSym's commit info
 */
public class RS implements Serializable {
    private static final long serialVersionUID = -2154697877210815835L;

    /** currency pair */
    private String symbol;
    /** subscribe amount */
    private Double mdEntrySize;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMdEntrySize() {
        return mdEntrySize;
    }

    public void setMdEntrySize(Double mdEntrySize) {
        this.mdEntrySize = mdEntrySize;
    }
}
