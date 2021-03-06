
package com.epam.jmp.webservice.model.task1.generated;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TicketWebService", targetNamespace = "http://task1.model.webservice.jmp.epam.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TicketWebService {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.math.BigDecimal
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookTicket", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.BookTicket")
    @ResponseWrapper(localName = "bookTicketResponse", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.BookTicketResponse")
    @Action(input = "http://task1.model.webservice.jmp.epam.com/TicketWebService/bookTicketRequest", output = "http://task1.model.webservice.jmp.epam.com/TicketWebService/bookTicketResponse")
    public BigDecimal bookTicket(
        @WebParam(name = "arg0", targetNamespace = "")
        Person arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.epam.jmp.webservice.model.task1.generated.Ticket
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTicket", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.GetTicket")
    @ResponseWrapper(localName = "getTicketResponse", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.GetTicketResponse")
    @Action(input = "http://task1.model.webservice.jmp.epam.com/TicketWebService/getTicketRequest", output = "http://task1.model.webservice.jmp.epam.com/TicketWebService/getTicketResponse")
    public Ticket getTicket(
        @WebParam(name = "arg0", targetNamespace = "")
        BigDecimal arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "refund", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.Refund")
    @ResponseWrapper(localName = "refundResponse", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.RefundResponse")
    @Action(input = "http://task1.model.webservice.jmp.epam.com/TicketWebService/refundRequest", output = "http://task1.model.webservice.jmp.epam.com/TicketWebService/refundResponse")
    public void refund(
        @WebParam(name = "arg0", targetNamespace = "")
        BigDecimal arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "payBookedTicked", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.PayBookedTicked")
    @ResponseWrapper(localName = "payBookedTickedResponse", targetNamespace = "http://task1.model.webservice.jmp.epam.com/", className = "com.epam.jmp.webservice.model.task1.generated.PayBookedTickedResponse")
    @Action(input = "http://task1.model.webservice.jmp.epam.com/TicketWebService/payBookedTickedRequest", output = "http://task1.model.webservice.jmp.epam.com/TicketWebService/payBookedTickedResponse")
    public void payBookedTicked(
        @WebParam(name = "arg0", targetNamespace = "")
        BigDecimal arg0);

}
