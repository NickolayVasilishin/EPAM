package com.epam.jmp.webservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public class Ticket {

    public enum State {
        BOOKED,
        PAYED
    }

    private BigDecimal number;
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String arrivalTime;
    private Money price;
    private State state;
    private Person passenger;

    public Ticket() {}

    public Ticket(BigDecimal number,
                  String departureCity,
                  String arrivalCity,
                  LocalDateTime departureTime,
                  LocalDateTime arrivalTime,
                  Money price,
                  State state,
                  Person passenger) {
        this.number = number;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime.toString();
        this.arrivalTime = arrivalTime.toString();
        this.price = price;
        this.state = state;
        this.passenger = passenger;
    }

    public void setState(State state) {
        this.state = state;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return LocalDateTime.parse(departureTime);
    }

    public LocalDateTime getArrivalTime() {
        return LocalDateTime.parse(arrivalTime);
    }

    public Money getPrice() {
        return price;
    }

    public State getState() {
        return state;
    }

    public Person getPassenger() {
        return passenger;
    }
}
