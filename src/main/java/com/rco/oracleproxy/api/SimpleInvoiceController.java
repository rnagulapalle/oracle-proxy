package com.rco.oracleproxy.api;

import com.rco.oracleproxy.client.InvoiceServiceClient;
import com.rco.oracleproxy.domain.invoice.SimpleInvoice;
import com.rco.oracleproxy.response.InvoiceResponse;
import com.rco.oracleproxy.response.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;


/**
 * TODO: clean up code and refactor common business logic
 * Created by rnagulapalle on 7/22/18.
 */
@Controller
@RequestMapping(value = "/v1/account-receivable/invoice")
@Api(tags = {"account-receivable-invoice"})
public class SimpleInvoiceController extends AbstractRestHandler {

    // TODO : add input validation
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an account receivable invoice resource.", notes = "Returns the URL of the new resource in the Location header.")
    /**
     * {
     "invoiceHeader": {
     "businessUnit": "GLOBAL CORP",
     "transactionType": "Invoice",
     "transactionSource": "Manual",
     "trxDate": "2018-04-30",
     "glDate": "2018-01-31",
     "billToCustomerName": "Guardant",
     "billToAccountNumber": "1",
     "paymentTermsName": "30 Net",
     "invoiceCurrencyCode": "USD"
     },
     "invoiceLine": {
     "lineNumber": 1,
     "description": "Test by raj",
     "quantity": 1,
     "unitCode": "Ea",
     "unitSellingPrice": 79,
     "currencyCode": "USD"
     }
     }
     */
    public ResponseEntity<?> createInvoice(@RequestBody SimpleInvoice invoice,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Hotel createdHotel = this.hotelService.createHotel(hotel);
        //response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
        // Setting keyStore Location
        String keyStoreLocation = "";
        String keyStorePass = "";
        String username = "matt.makowsky1@perficient.com";
        String password = "Welcome123";
        // Calling the invoice service invocation
        // The createInvoice method calls the Http Post to invoke the
        //service
        InvoiceServiceClient invoiceServiceClient = new InvoiceServiceClient();
        String res =
                invoiceServiceClient.createInvoice("egtu-test.fa.us2.oraclecloud.com",-1,
                        username, password, invoice,
                        keyStoreLocation, keyStorePass);
        // Parse the response to read the service output details and
        //handle errors if any.
        System.out.println(res);
        if (res != null && !res.isEmpty()) {
            System.out.println();
            InputSource source = new InputSource(new
                    StringReader(res));
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(source);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String servStatus = xpath.evaluate("Envelope/Body/createSimpleInvoiceResponse/result/ServiceStatus", document);
            String trxNumber = xpath.evaluate("Envelope/Body/createSimpleInvoiceResponse/result/TransactionNumber", document);

            if ("".equals(servStatus) && "".equals(trxNumber)) {
                System.out.println("Service Errored. Parse the Response to review the Error Message ");
                printDocument(document, System.out);
                return new ResponseEntity<>("Failed to create an invoice due to SOAP API failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            System.out.println("Service Status = " + servStatus);
            System.out.println("Transaction Number = " + trxNumber);
            InvoiceResponse invoiceResponse = new InvoiceResponse();
            invoiceResponse.setStatus(new Status(servStatus));
            invoiceResponse.setTransactionNumber(Long.valueOf(trxNumber));
            

            return new ResponseEntity<>(invoiceResponse, HttpStatus.OK);
        }

        //response.setHeader("Location", "hello")
        return new ResponseEntity<>("Oracle SOAP API failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }
}
