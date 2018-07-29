package com.rco.oracleproxy.response;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class InvoiceResponse {

    private long transactionNumber;
    private Status status;

    public long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
