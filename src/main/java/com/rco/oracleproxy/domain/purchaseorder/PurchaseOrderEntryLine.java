package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class PurchaseOrderEntryLine {
    private int lineNumber;
    private int lineTypeId;
    private String itemNumber;
    private String unitOfMeasureCode;
    private Quantity quantity;
    private Price price;
    private PurchaseOrderEntrySchedule purchaseOrderEntrySchedule;

    public PurchaseOrderEntryLine(){}
    public PurchaseOrderEntryLine(int lineNumber, int lineTypeId, String itemNumber, String unitOfMeasureCode,
            Quantity quantity, Price price,
            PurchaseOrderEntrySchedule purchaseOrderEntrySchedule) {
        this.lineNumber = lineNumber;
        this.lineTypeId = lineTypeId;
        this.itemNumber = itemNumber;
        this.unitOfMeasureCode = unitOfMeasureCode;
        this.quantity = quantity;
        this.price = price;
        this.purchaseOrderEntrySchedule = purchaseOrderEntrySchedule;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeId(int lineTypeId) {
        this.lineTypeId = lineTypeId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(String unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public PurchaseOrderEntrySchedule getPurchaseOrderEntrySchedule() {
        return purchaseOrderEntrySchedule;
    }

    public void setPurchaseOrderEntrySchedule(
            PurchaseOrderEntrySchedule purchaseOrderEntrySchedule) {
        this.purchaseOrderEntrySchedule = purchaseOrderEntrySchedule;
    }
}
