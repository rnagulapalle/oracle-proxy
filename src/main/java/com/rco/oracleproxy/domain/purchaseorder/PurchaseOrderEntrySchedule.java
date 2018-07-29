package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/29/18.
 */
public class PurchaseOrderEntrySchedule {
    private String shipToLocationCode;
    private String shipToOrganizationCode;
    private String needByDate;
    private String purchaseOrderEntryDistribution;

    public PurchaseOrderEntrySchedule(){}
    public PurchaseOrderEntrySchedule(String shipToLocationCode, String shipToOrganizationCode, String needByDate,
            String purchaseOrderEntryDistribution) {
        this.shipToLocationCode = shipToLocationCode;
        this.shipToOrganizationCode = shipToOrganizationCode;
        this.needByDate = needByDate;
        this.purchaseOrderEntryDistribution = purchaseOrderEntryDistribution;
    }

    public String getShipToLocationCode() {
        return shipToLocationCode;
    }

    public void setShipToLocationCode(String shipToLocationCode) {
        this.shipToLocationCode = shipToLocationCode;
    }

    public String getShipToOrganizationCode() {
        return shipToOrganizationCode;
    }

    public void setShipToOrganizationCode(String shipToOrganizationCode) {
        this.shipToOrganizationCode = shipToOrganizationCode;
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
    }

    public String getPurchaseOrderEntryDistribution() {
        return purchaseOrderEntryDistribution;
    }

    public void setPurchaseOrderEntryDistribution(
            String purchaseOrderEntryDistribution) {
        this.purchaseOrderEntryDistribution = purchaseOrderEntryDistribution;
    }
}
