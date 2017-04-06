package com.epam.jmp.jdbc.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String surname;
    private final Date birthdate;
    private LocalDate dateOfBirth;
    private List<User> friends;

    public User(int id, String name, String surname, Date birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
