package com.rco.oracleproxy.domain.invoice;

/**
 * Created by rnagulapalle on 7/22/18.
 */
public class Invoice {


    private InvoiceHeader invoiceHeader;
    private InvoiceLine invoiceLine;

    public Invoice() {
    }

    public Invoice(InvoiceHeader invoiceHeader, InvoiceLine invoiceLine) {
        this.invoiceHeader = invoiceHeader;
        this.invoiceLine = invoiceLine;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }
}