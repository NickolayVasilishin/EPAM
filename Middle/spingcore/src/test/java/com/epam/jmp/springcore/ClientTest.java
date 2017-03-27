package com.epam.jmp.springcore;

import com.epam.jmp.springcore.bean.Person;
import com.epam.jmp.springcore.bean.Ticket;
import com.epam.jmp.springcore.bean.TicketService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ClientTest {
    ApplicationContext context;
    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    @Test
    // Weird namings, but easier to read
    public void testCase_A_F() {
        Person expected = new Person("Isaak", "Asimov", null, "4-08-1919");
        Person passenger = context.getBean("passenger", Person.class);
        assertEquals(expected, passenger);
    }

    @Test
    public void testCase_B() {
        Ticket ticket = context.getBean("ticket", Ticket.class);
        assertNotNull(ticket);
        System.out.println(ticket);
    }

    @Test
    public void testCase_C_D() {
        TicketService singletonTicketService = context.getBean("singletonTicketService", TicketService.class);
        Ticket ticket = singletonTicketService.bookTicket();
        Ticket otherTicket = context.getBean("ticket", Ticket.class);

        assertNotEquals(otherTicket, ticket);
        assertEquals(ticket.getNumber(), otherTicket.getNumber());
    }

    @Test
    public void testCase_E() {
        TicketService lazyTicketService = context.getBean("lazyTicketService", TicketService.class);
        lazyTicketService.workAllDay();
    }

    @After
    public void tearDown() {
        if (context != null) {
            ((AbstractApplicationContext) context).close();
        }
    }

}