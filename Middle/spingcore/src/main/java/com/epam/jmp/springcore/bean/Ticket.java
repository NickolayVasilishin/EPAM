package com.epam.jmp.springcore.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

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
    private long secret = new Random().nextLong();

    public Ticket() {}

    public Ticket(BigDecimal number,
                  String departureCity,
                  String arrivalCity,
                  String departureTime,
                  String arrivalTime,
                  Money price,
                  State state,
                  Person passenger) {
        this.number = number;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.state = state;
        this.passenger = passenger;
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

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", passenger=" + passenger +
                ", secret=" + secret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (secret != ticket.secret) return false;
        if (number != null ? !number.equals(ticket.number) : ticket.number != null) return false;
        if (departureCity != null ? !departureCity.equals(ticket.departureCity) : ticket.departureCity != null)
            return false;
        if (arrivalCity != null ? !arrivalCity.equals(ticket.arrivalCity) : ticket.arrivalCity != null) return false;
        if (departureTime != null ? !departureTime.equals(ticket.departureTime) : ticket.departureTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(ticket.arrivalTime) : ticket.arrivalTime != null) return false;
        if (price != null ? !price.equals(ticket.price) : ticket.price != null) return false;
        if (state != ticket.state) return false;
        return passenger != null ? passenger.equals(ticket.passenger) : ticket.passenger == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (departureCity != null ? departureCity.hashCode() : 0);
        result = 31 * result + (arrivalCity != null ? arrivalCity.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        result = 31 * result + (int) (secret ^ (secret >>> 32));
        return result;
    }
}
