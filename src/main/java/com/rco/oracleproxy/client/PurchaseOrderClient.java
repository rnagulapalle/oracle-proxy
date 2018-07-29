package com.rco.oracleproxy.client;

import com.rco.oracleproxy.domain.purchaseorder.PurchaseOrder;
import com.rco.oracleproxy.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * TODO: clean up code and refactor common business logic
 * Created by rnagulapalle on 7/28/18.
 */
public class PurchaseOrderClient {

    public PurchaseOrderClient() {
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

    public String constructPayload(PurchaseOrder purchaseOrder) {
        String payload = "<soapenv:Envelope\n"
                + "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
                + "    xmlns:typ=\"http://xmlns.oracle.com/apps/prc/po/editDocument/purchaseOrderServiceV2/types/\"\n"
                + "    xmlns:pur=\"http://xmlns.oracle.com/apps/prc/po/editDocument/purchaseOrderServiceV2/\"\n"
                + "    xmlns:draf=\"http://xmlns.oracle.com/apps/prc/po/editDocument/flex/draftPurchaseOrderDistribution/\"\n"
                + "    xmlns:pjc=\"http://xmlns.oracle.com/apps/prc/po/commonPo/flex/PJCPoDraftDistribution/\"\n"
                + "    xmlns:draf1=\"http://xmlns.oracle.com/apps/prc/po/editDocument/flex/draftPurchasingDocumentSchedule/\"\n"
                + "    xmlns:draf2=\"http://xmlns.oracle.com/apps/prc/po/editDocument/flex/draftPurchasingDocumentLine/\"\n"
                + "    xmlns:draf3=\"http://xmlns.oracle.com/apps/prc/po/editDocument/flex/draftPurchasingDocumentHeader/\">\n"
                + "    <soapenv:Header/>\n"
                + "    <soapenv:Body>\n"
                + "        <typ:createPurchaseOrder>\n"
                + "            <typ:createOrderEntry>\n"
                + "                <pur:ProcurementBusinessUnit>"+purchaseOrder.getPurchaseOrderEntry().getProcurementBusinessUnit()+"</pur:ProcurementBusinessUnit>\n"
                + "                <pur:RequisitioningBusinessUnit>"+purchaseOrder.getPurchaseOrderEntry().getRequisitioningBusinessUnit()+"</pur:RequisitioningBusinessUnit>\n"
                + "                <pur:ApprovalActionCode>"+purchaseOrder.getPurchaseOrderEntry().getApprovalActionCode()+"</pur:ApprovalActionCode>\n"
                + "                <pur:BuyerName>"+purchaseOrder.getPurchaseOrderEntry().getBuyerName()+"</pur:BuyerName>\n"
                + "                <pur:Supplier>"+purchaseOrder.getPurchaseOrderEntry().getSupplier()+"</pur:Supplier>\n"
                + "                <pur:SupplierSiteCode>"+purchaseOrder.getPurchaseOrderEntry().getSupplierSiteCode()+"</pur:SupplierSiteCode>\n"
                + "                <pur:DocumentDescription>"+purchaseOrder.getPurchaseOrderEntry().getDocumentDescription()+"</pur:DocumentDescription>\n"
                + "                <pur:PurchaseOrderEntryLine>\n"
                + "                    <pur:LineNumber>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getLineNumber()+"</pur:LineNumber>\n"
                + "                    <pur:LineTypeId>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getLineTypeId()+"</pur:LineTypeId>\n"
                + "                    <pur:ItemNumber>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getItemNumber()+"</pur:ItemNumber>\n"
                + "                    <pur:UnitOfMeasureCode>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getUnitOfMeasureCode()+"</pur:UnitOfMeasureCode>\n"
                + "                    <pur:Quantity unitCode=\""+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getQuantity().getUnitCode()+"\">"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getQuantity().getQuantity()+"</pur:Quantity>\n"
                + "                    <pur:Price currencyCode=\""+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPrice().getCurrencyCode() +"\">"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPrice().getPrice()+"</pur:Price>\n"
                + "                    <pur:PurchaseOrderEntrySchedule>\n"
                + "                        <pur:ShipToLocationCode>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPurchaseOrderEntrySchedule().getShipToLocationCode()+"</pur:ShipToLocationCode>\n"
                + "                        <pur:ShipToOrganizationCode>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPurchaseOrderEntrySchedule().getShipToOrganizationCode()+"</pur:ShipToOrganizationCode>\n"
                + "                        <pur:NeedByDate>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPurchaseOrderEntrySchedule().getNeedByDate()+"</pur:NeedByDate>\n"
                + "                        <pur:PurchaseOrderEntryDistribution>"+purchaseOrder.getPurchaseOrderEntry().getPurchaseOrderEntryLine().getPurchaseOrderEntrySchedule().getPurchaseOrderEntryDistribution()+"</pur:PurchaseOrderEntryDistribution>\n"
                + "                    </pur:PurchaseOrderEntrySchedule>\n"
                + "                </pur:PurchaseOrderEntryLine>\n"
                + "            </typ:createOrderEntry>\n"
                + "        </typ:createPurchaseOrder>\n"
                + "    </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        return payload;
    }
    public String createPurchaseOrder(String hostName, int port, String username,
            String password, PurchaseOrder purchaseOrder, String keyStoreLocation,
            String keyStorePassword) throws Exception {
        if (port < 0)
            this.setWebService("https://" + hostName +
                    "/fscmService/PurchaseOrderServiceV2");
        else
            this.setWebService("https://" + hostName + ":" + port +
                    "/fscmService/PurchaseOrderServiceV2");
        this.setUsername(username);
        this.setPassword(password);
        // Construct the XML input payload
        String reqPayload = this.constructPayload(purchaseOrder);
        this.setInputPayload(reqPayload);
        // Invoke the service via a http secure connection
        String response =
                httpPost(getWebService() + "?invoke=",
                        getInputPayload(), getUsername() +
                                ":" + getPassword(), keyStoreLocation, keyStorePassword);
        return response;
    }
}
