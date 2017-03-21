
package com.epam.jmp.webservice.model.task1.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.jmp.webservice.model.task1.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RefundResponse_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "refundResponse");
    private final static QName _BookTicket_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "bookTicket");
    private final static QName _PayBookedTickedResponse_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "payBookedTickedResponse");
    private final static QName _Person_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "Person");
    private final static QName _GetTicket_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "getTicket");
    private final static QName _PayBookedTicked_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "payBookedTicked");
    private final static QName _Refund_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "refund");
    private final static QName _BookTicketResponse_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "bookTicketResponse");
    private final static QName _GetTicketResponse_QNAME = new QName("http://task1.model.webservice.jmp.epam.com/", "getTicketResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.jmp.webservice.model.task1.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookTicket }
     * 
     */
    public BookTicket createBookTicket() {
        return new BookTicket();
    }

    /**
     * Create an instance of {@link PayBookedTickedResponse }
     * 
     */
    public PayBookedTickedResponse createPayBookedTickedResponse() {
        return new PayBookedTickedResponse();
    }

    /**
     * Create an instance of {@link RefundResponse }
     * 
     */
    public RefundResponse createRefundResponse() {
        return new RefundResponse();
    }

    /**
     * Create an instance of {@link GetTicketResponse }
     * 
     */
    public GetTicketResponse createGetTicketResponse() {
        return new GetTicketResponse();
    }

    /**
     * Create an instance of {@link BookTicketResponse }
     * 
     */
    public BookTicketResponse createBookTicketResponse() {
        return new BookTicketResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link GetTicket }
     * 
     */
    public GetTicket createGetTicket() {
        return new GetTicket();
    }

    /**
     * Create an instance of {@link PayBookedTicked }
     * 
     */
    public PayBookedTicked createPayBookedTicked() {
        return new PayBookedTicked();
    }

    /**
     * Create an instance of {@link Refund }
     * 
     */
    public Refund createRefund() {
        return new Refund();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefundResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "refundResponse")
    public JAXBElement<RefundResponse> createRefundResponse(RefundResponse value) {
        return new JAXBElement<RefundResponse>(_RefundResponse_QNAME, RefundResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "bookTicket")
    public JAXBElement<BookTicket> createBookTicket(BookTicket value) {
        return new JAXBElement<BookTicket>(_BookTicket_QNAME, BookTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayBookedTickedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "payBookedTickedResponse")
    public JAXBElement<PayBookedTickedResponse> createPayBookedTickedResponse(PayBookedTickedResponse value) {
        return new JAXBElement<PayBookedTickedResponse>(_PayBookedTickedResponse_QNAME, PayBookedTickedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "Person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "getTicket")
    public JAXBElement<GetTicket> createGetTicket(GetTicket value) {
        return new JAXBElement<GetTicket>(_GetTicket_QNAME, GetTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayBookedTicked }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "payBookedTicked")
    public JAXBElement<PayBookedTicked> createPayBookedTicked(PayBookedTicked value) {
        return new JAXBElement<PayBookedTicked>(_PayBookedTicked_QNAME, PayBookedTicked.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "refund")
    public JAXBElement<Refund> createRefund(Refund value) {
        return new JAXBElement<Refund>(_Refund_QNAME, Refund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "bookTicketResponse")
    public JAXBElement<BookTicketResponse> createBookTicketResponse(BookTicketResponse value) {
        return new JAXBElement<BookTicketResponse>(_BookTicketResponse_QNAME, BookTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://task1.model.webservice.jmp.epam.com/", name = "getTicketResponse")
    public JAXBElement<GetTicketResponse> createGetTicketResponse(GetTicketResponse value) {
        return new JAXBElement<GetTicketResponse>(_GetTicketResponse_QNAME, GetTicketResponse.class, null, value);
    }

}
