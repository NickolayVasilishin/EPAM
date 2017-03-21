
package com.epam.jmp.webservice.model.task1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TicketWebService", targetNamespace = "http://task1.model.webservice.jmp.epam.com", wsdlLocation = "file:/C:/my/repos/EPAM/Middle/webservice/src/main/java/com/epam/jmp/webservice/model/task1/TicketWebService.wsdl")
public class TicketWebService
    extends Service
{

    private final static URL TICKETWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException TICKETWEBSERVICE_EXCEPTION;
    private final static QName TICKETWEBSERVICE_QNAME = new QName("http://task1.model.webservice.jmp.epam.com", "TicketWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/my/repos/EPAM/Middle/webservice/src/main/java/com/epam/jmp/webservice/model/task1/TicketWebService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TICKETWEBSERVICE_WSDL_LOCATION = url;
        TICKETWEBSERVICE_EXCEPTION = e;
    }

    public TicketWebService() {
        super(__getWsdlLocation(), TICKETWEBSERVICE_QNAME);
    }

    public TicketWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TICKETWEBSERVICE_QNAME, features);
    }

    public TicketWebService(URL wsdlLocation) {
        super(wsdlLocation, TICKETWEBSERVICE_QNAME);
    }

    public TicketWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TICKETWEBSERVICE_QNAME, features);
    }

    public TicketWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TicketWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Tickets
     */
    @WebEndpoint(name = "TicketWebService")
    public Tickets getTicketWebService() {
        return super.getPort(new QName("http://task1.model.webservice.jmp.epam.com", "TicketWebService"), Tickets.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Tickets
     */
    @WebEndpoint(name = "TicketWebService")
    public Tickets getTicketWebService(WebServiceFeature... features) {
        return super.getPort(new QName("http://task1.model.webservice.jmp.epam.com", "TicketWebService"), Tickets.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TICKETWEBSERVICE_EXCEPTION!= null) {
            throw TICKETWEBSERVICE_EXCEPTION;
        }
        return TICKETWEBSERVICE_WSDL_LOCATION;
    }

}