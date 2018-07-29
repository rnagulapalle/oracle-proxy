package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class PurchaseOrder {

    PurchaseOrderEntry purchaseOrderEntry;

    public PurchaseOrder(){}

    public PurchaseOrder(PurchaseOrderEntry purchaseOrderEntry) {
        this.purchaseOrderEntry = purchaseOrderEntry;
    }

    public PurchaseOrderEntry getPurchaseOrderEntry() {
        return purchaseOrderEntry;
    }

    public void setPurchaseOrderEntry(PurchaseOrderEntry purchaseOrderEntry) {
        this.purchaseOrderEntry = purchaseOrderEntry;
    }
}
