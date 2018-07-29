package com.rco.oracleproxy.domain.purchaseorder;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class PurchaseOrderEntry {
    private String procurementBusinessUnit;
    private String requisitioningBusinessUnit;
    private String approvalActionCode;
    private String buyerName;
    private String supplier;
    private String supplierSiteCode;
    private String documentDescription;
    private PurchaseOrderEntryLine purchaseOrderEntryLine;

    public PurchaseOrderEntry(){}
    public PurchaseOrderEntry(String procurementBusinessUnit, String requisitioningBusinessUnit,
            String approvalActionCode, String buyerName, String supplier, String supplierSiteCode,
            String documentDescription,
            PurchaseOrderEntryLine purchaseOrderEntryLine) {
        this.procurementBusinessUnit = procurementBusinessUnit;
        this.requisitioningBusinessUnit = requisitioningBusinessUnit;
        this.approvalActionCode = approvalActionCode;
        this.buyerName = buyerName;
        this.supplier = supplier;
        this.supplierSiteCode = supplierSiteCode;
        this.documentDescription = documentDescription;
        this.purchaseOrderEntryLine = purchaseOrderEntryLine;
    }

    public String getProcurementBusinessUnit() {
        return procurementBusinessUnit;
    }

    public void setProcurementBusinessUnit(String procurementBusinessUnit) {
        this.procurementBusinessUnit = procurementBusinessUnit;
    }

    public String getRequisitioningBusinessUnit() {
        return requisitioningBusinessUnit;
    }

    public void setRequisitioningBusinessUnit(String requisitioningBusinessUnit) {
        this.requisitioningBusinessUnit = requisitioningBusinessUnit;
    }

    public String getApprovalActionCode() {
        return approvalActionCode;
    }

    public void setApprovalActionCode(String approvalActionCode) {
        this.approvalActionCode = approvalActionCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierSiteCode() {
        return supplierSiteCode;
    }

    public void setSupplierSiteCode(String supplierSiteCode) {
        this.supplierSiteCode = supplierSiteCode;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public PurchaseOrderEntryLine getPurchaseOrderEntryLine() {
        return purchaseOrderEntryLine;
    }

    public void setPurchaseOrderEntryLine(PurchaseOrderEntryLine purchaseOrderEntryLine) {
        this.purchaseOrderEntryLine = purchaseOrderEntryLine;
    }
}
