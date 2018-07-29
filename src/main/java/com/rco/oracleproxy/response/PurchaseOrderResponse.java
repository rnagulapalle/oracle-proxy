package com.rco.oracleproxy.response;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class PurchaseOrderResponse {

    private long purchaseOrderId;
    private Status status;

    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
