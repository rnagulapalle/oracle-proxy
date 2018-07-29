package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/29/18.
 */
public class Price {

    private int price;
    private String currencyCode;

    public Price(){}

    public Price(int price, String currencyCode) {
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
