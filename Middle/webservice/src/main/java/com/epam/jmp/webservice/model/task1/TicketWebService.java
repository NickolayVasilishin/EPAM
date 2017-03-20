package com.epam.jmp.webservice.model.task1;
import com.epam.jmp.webservice.model.Person;
import com.epam.jmp.webservice.model.Ticket;
import com.epam.jmp.webservice.model.TicketService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebService(name = "tickets")
public class TicketWebService {
    private final TicketService ticketService = TicketService.getInstance();

    @WebMethod
    public BigDecimal bookTicket(Person person, String departureCity, String arrivalCity, String departureTime, String arrivalTime) {
        return ticketService.bookTicket(person, departureCity, arrivalCity, LocalDateTime.parse(departureTime), LocalDateTime.parse(arrivalTime));
    }

    @WebMethod
    public Ticket getTicket(BigDecimal uuid) {
        return ticketService.getTicket(uuid);
    }

    @WebMethod
    public void payBookedTicked(BigDecimal uuid) {
        ticketService.payBookedTicked(uuid);
    }

    @WebMethod
    public void refund(BigDecimal uuid) {
        ticketService.refund(uuid);
    }

  public static void main(String[] argv) {
    Object implementor = new TicketWebService();
    String address = "http://localhost:9000/TicketWebService";
    Endpoint.publish(address, implementor);
  }
}
