package com.rco.oracleproxy.response;

/**
 * Created by rnagulapalle on 7/28/18.
 */
public class Status {

    private String message;

    public Status(String servStatus) {
        this.message = servStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
