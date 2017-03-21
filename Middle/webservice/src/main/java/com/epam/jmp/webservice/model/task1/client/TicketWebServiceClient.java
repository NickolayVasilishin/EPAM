package com.epam.jmp.webservice.model.task1.client;

import com.epam.jmp.webservice.model.task1.generated.Person;
import com.epam.jmp.webservice.model.task1.generated.TicketWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.math.BigDecimal;
import java.net.URL;

public class TicketWebServiceClient {


    public static void main(String[] args) throws Exception {

        Service service = Service.create(new URL("http://localhost:9000/TicketWebService/?wsdl"), new QName("http://task1.model.webservice.jmp.epam.com/", "TicketWebServiceService"));

        TicketWebService ticketWebService = service.getPort(TicketWebService.class);

        System.out.println(ticketWebService.bookTicket(
                new Person("Isaak", "Asimov", "", "4-08-1919"),
                "Saint-Petersburg",
                "Moscow",
                "2017-12-03T10:15:00",
                "2017-12-04T10:00:00")
        );

        BigDecimal ticketId = new BigDecimal("11675586033148451950479435610212677204");
        System.out.println(ticketWebService.getTicket(ticketId));

        ticketWebService.payBookedTicked(ticketId);
        System.out.println(ticketWebService.getTicket(ticketId));

        ticketWebService.refund(ticketId);
        System.out.println(ticketWebService.getTicket(ticketId));

    }

}


