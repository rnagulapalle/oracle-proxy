package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/29/18.
 */
public class Quantity {

    private String unitCode;
    private int quantity;

    public Quantity(){}

    public Quantity(String unitCode, int quantity) {
        this.unitCode = unitCode;
        this.quantity = quantity;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
