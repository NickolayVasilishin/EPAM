package com.epam.jmp.springcore.bean;

public class TicketService {
    private Ticket ticket;

    public TicketService() {
    }

    public Ticket bookTicket() {
        return null;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void workAllDay() {
        System.out.println("We are working all day");
    }
}
