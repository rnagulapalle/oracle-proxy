package com.rco.oracleproxy.client;

/**
 * Created by rnagulapalle on 7/18/18.
 */

import com.rco.oracleproxy.domain.invoice.Invoice;
import com.rco.oracleproxy.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

// TODO: clean up code and refactor common business logic
public class InvoiceServiceClient {

    public InvoiceServiceClient() {
        super();
    }

    private static String serviceURL;
    private static String inputPayload;
    private static String username;
    private static String password;
    private static String userToken;
    private static String outputPayload;

    public static String httpPost(String destUrl, String postData,
            String authStr, String keyStoreLocation,
            String keyStorePassword) throws Exception {
        System.out.println();
        System.out.println("Invoking the Service");
        // Setting the KeyStore Properties
//        System.setProperty("javax.net.ssl.trustStore", keyStoreLocation);
//        System.setProperty("javax.net.ssl.trustStorePassword",
             //   keyStorePassword);
        // Open the HTTP connection and set the connection properties
        URL url = new URL(destUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        if (conn == null) {
            return null;
        }
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setFollowRedirects(true);
        conn.setAllowUserInteraction(false);
        conn.setRequestMethod("POST");

        // Set the Authorization property for the HTTP connection using the
        //username and password
        byte[] authBytes = authStr.getBytes("UTF-8");
        String auth = Base64.byteArrayToBase64(authBytes);
        conn.setRequestProperty("Authorization", "Basic " + auth);
        // Post the http request. This will invoke the Invoice Web Service for
        // creating the invoice
        OutputStream out = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
        writer.write(postData);
        writer.close();
        out.close();
        try {
            InputStream errIs = conn.getErrorStream();
            if (errIs != null) {
                String err = getString(errIs);
                if (err != null && !err.isEmpty()) {
                    System.out.println(err);
                }
                errIs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Read the response and return it to the calling Java API
        String response = null;
        try {
            InputStream in = conn.getInputStream();
            if (in != null) {
                response = getString(in);
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return response;
    }
    public static String getString(InputStream errIs) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(errIs));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    public void setInputPayload(String inputPayload) {
        this.inputPayload = inputPayload;
    }
    public String getInputPayload() {
        return this.inputPayload;
    }
    public void setWebService(String webService) {
        this.serviceURL = webService;
    }
    public static String getWebService() {
        return serviceURL;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public static String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static String getPassword() {
        return password;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public String getUserToken() {
        return userToken;
    }
    public void setOutputPayload(String outputPayload) {
        this.outputPayload = outputPayload;
    }
    public String getOutputPayload() {
        return outputPayload;
    }

    public String constructPayload(Invoice invoice) {
        String payload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:inv=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/invoices/invoiceService/\" xmlns:tran=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionHeaderDff/\" xmlns:tran1=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionHeaderGdf/\" xmlns:tran2=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionInterfaceHeaderDff/\" xmlns:tran3=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionInterfaceLineDff/\" xmlns:tran4=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionLineDff/\" xmlns:tran5=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/shared/model/flex/TransactionLineGdf/\" xmlns:typ=\"http://xmlns.oracle.com/apps/financials/receivables/transactions/invoices/invoiceService/types/\">\n"
                + "   <soapenv:Header />\n"
                + "   <soapenv:Body>\n"
                + "      <typ:createSimpleInvoice>\n"
                + "         <typ:invoiceHeaderInformation>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:BusinessUnit>"+invoice.getInvoiceHeader().getBusinessUnit()+"</inv:BusinessUnit>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:TransactionSource>"+invoice.getInvoiceHeader().getTransactionSource()+"</inv:TransactionSource>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:TransactionType>"+invoice.getInvoiceHeader().getTransactionType()+"</inv:TransactionType>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:TrxDate>"+invoice.getInvoiceHeader().getTrxDate()+"</inv:TrxDate>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:GlDate>"+invoice.getInvoiceHeader().getGlDate()+"</inv:GlDate>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:BillToCustomerName>"+invoice.getInvoiceHeader().getBillToCustomerName()+"</inv:BillToCustomerName>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:BillToAccountNumber>"+invoice.getInvoiceHeader().getBillToAccountNumber()+"</inv:BillToAccountNumber>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:PaymentTermsName>"+invoice.getInvoiceHeader().getPaymentTermsName()+"</inv:PaymentTermsName>\n"
                + "            <!--Optional:-->\n"
                + "            <inv:InvoiceCurrencyCode>"+invoice.getInvoiceHeader().getInvoiceCurrencyCode()+"</inv:InvoiceCurrencyCode>\n"
                + "            <!--Zero or more repetitions:-->\n"
                + "            <inv:InvoiceLine>\n"
                + "               <!--Optional:-->\n"
                + "               <inv:LineNumber>"+invoice.getInvoiceLine().getLineNumber()+"</inv:LineNumber>\n"
                + "               <!--Optional:-->\n"
                + "               <inv:Description>"+invoice.getInvoiceLine().getDescription()+"</inv:Description>\n"
                + "               <!--Optional:-->\n"
                + "               <inv:Quantity unitCode=\""+invoice.getInvoiceLine().getUnitCode()+"\">"+invoice.getInvoiceLine().getQuantity()+"</inv:Quantity>\n"
                + "               <!--Optional:-->\n"
                + "               <inv:UnitSellingPrice currencyCode=\""+invoice.getInvoiceLine().getCurrencyCode()+"\">"+invoice.getInvoiceLine().getUnitSellingPrice()+"</inv:UnitSellingPrice>\n"
                + "               <!--Optional:-->\n"
                + "            </inv:InvoiceLine>\n"
                + "         </typ:invoiceHeaderInformation>\n"
                + "      </typ:createSimpleInvoice>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        return payload;
    }
    public String createInvoice(String hostName, int port, String username,
            String password, Invoice invoice, String keyStoreLocation,
            String keyStorePassword) throws Exception {
        if (port < 0)
            this.setWebService("https://" + hostName +
                    "/finArTrxnsInvoices/InvoiceService");
        else
            this.setWebService("https://" + hostName + ":" + port +
                    "/finArTrxnsInvoices/InvoiceService");
        this.setUsername(username);
        this.setPassword(password);
        // Construct the XML input payload
        String reqPayload = this.constructPayload(invoice);
        this.setInputPayload(reqPayload);
        // Invoke the service via a http secure connection
        String response =
                httpPost(getWebService() + "?invoke=",
                        getInputPayload(), getUsername() +
                                ":" + getPassword(), keyStoreLocation, keyStorePassword);
        return response;
    }
}
