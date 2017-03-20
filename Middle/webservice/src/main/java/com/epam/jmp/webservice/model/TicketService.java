package com.epam.jmp.webservice.model;

import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Над объектом «Билет на поезд» допустимы следующие операции:

  - забронировать билет на поезд. При выполнении этой операции билету на поезд присваивается уникальный номер,
    устанавливается состояние «забронирован», и билет сохраняется в системе. В результате выполнения этой
    операции возвращается уникальный номер билета.

  - получить билет по номеру. В результате этой операции возвращается билет с указанным номером,
    ранее сохраненный в системе. Если билета с указанным номером в системе нет, то должно быть возвращено
    пустое значение.

  - оплатить билет на поезд. При выполнении этой операции билету устанавливается состояние
    «оплачен». Оплатить можно только забронированный ранее билет.

  - вернуть билет на поезд. При выполнении этой операции билет удаляется из системы.
 */

public class TicketService {
    private static TicketService ourInstance = new TicketService();

    public static TicketService getInstance() {
        return ourInstance;
    }

    private Map<BigDecimal, Ticket> ticketMap;
    private TicketService() {
        ticketMap = new ConcurrentHashMap<>();
    }

    public BigDecimal bookTicket(Person person,
                           String departureCity,
                           String arrivalCity,
                           LocalDateTime departureTime,
                           LocalDateTime arrivalTime) {

        BigDecimal uuid = generateTicketId();
        Money price = getPrice(departureCity, arrivalCity, departureTime, arrivalTime);

        Ticket ticket = new Ticket(uuid,
                departureCity,
                arrivalCity,
                departureTime,
                arrivalTime,
                price,
                Ticket.State.BOOKED,
                person);

        ticketMap.put(uuid, ticket);

        return uuid;
    }

    public Ticket getTicket(BigDecimal uuid) {
        return ticketMap.get(uuid);
    }

    public void payBookedTicked(BigDecimal uuid) {
        Ticket ticket = getTicket(uuid);
        if (ticket != null) {
            ticket.setState(Ticket.State.PAYED);
        }
    }

    public void refund(BigDecimal uuid) {
        ticketMap.remove(uuid);
    }

    private Money getPrice(String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        int sum = departureCity.hashCode() + arrivalCity.hashCode() + departureTime.getHour() + arrivalTime.getHour();
        sum %= 1000;
        Money money = new Money(new BigDecimal(sum));
        return money;
    }

    private BigDecimal generateTicketId() {
        String id = UUID.randomUUID().toString();
        String hexUUID = id.replaceAll("-", "");
        BigDecimal uuid = new BigDecimal(new BigInteger(hexUUID , 16));
        return uuid;
    }


}
