package com.rco.oracleproxy.domain.invoice;

/**
 * Created by rnagulapalle on 7/22/18.
 */
public class SimpleInvoice {


    private InvoiceHeaderInformation invoiceHeaderInformation;
    private InvoiceLine[] invoiceLines;

    public SimpleInvoice() {
    }

    public SimpleInvoice(InvoiceHeaderInformation invoiceHeaderInformation, InvoiceLine[] invoiceLines) {
        this.invoiceHeaderInformation = invoiceHeaderInformation;
        this.invoiceLines = invoiceLines;
    }

    public InvoiceHeaderInformation getInvoiceHeaderInformation() {
        return invoiceHeaderInformation;
    }

    public void setInvoiceHeaderInformation(InvoiceHeaderInformation invoiceHeaderInformation) {
        this.invoiceHeaderInformation = invoiceHeaderInformation;
    }

    public InvoiceLine[] getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(InvoiceLine[] invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}