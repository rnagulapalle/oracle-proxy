package com.rco.oracleproxy.domain.invoice;

/**
 * Created by rnagulapalle on 7/22/18.
 */
public class InvoiceHeaderInformation {

    private String transactionType;

    private String transactionSource;

    private String businessUnit;

    private String trxDate;

    private String glDate;

    private String billToCustomerName;

    private String billToAccountNumber;

    private String paymentTermsName;

    private String invoiceCurrencyCode;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getBillToCustomerName() {
        return billToCustomerName;
    }

    public void setBillToCustomerName(String billToCustomerName) {
        this.billToCustomerName = billToCustomerName;
    }

    public String getBillToAccountNumber() {
        return billToAccountNumber;
    }

    public void setBillToAccountNumber(String billToAccountNumber) {
        this.billToAccountNumber = billToAccountNumber;
    }

    public String getPaymentTermsName() {
        return paymentTermsName;
    }

    public void setPaymentTermsName(String paymentTermsName) {
        this.paymentTermsName = paymentTermsName;
    }

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
    }

    public String getGlDate() {
        return glDate;
    }

    public void setGlDate(String glDate) {
        this.glDate = glDate;
    }
}
