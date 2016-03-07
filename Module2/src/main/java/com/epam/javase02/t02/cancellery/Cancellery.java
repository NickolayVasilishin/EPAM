package com.epam.javase02.t02.cancellery;

import com.epam.javase02.t02.item.CancelleryItem;
import com.epam.javase02.t02.preson.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 04.03.2016.
 */
public class Cancellery {
    private Map<String, Person> employees;

    public Cancellery() {
        employees = new HashMap<>();
    }

    public Cancellery registerEmployee(Person person) {
        employees.put(person.getName(), person);
        return this;
    }

    public int calculateCost(Person person) {
        return employees.get(person.getName()).showItems().stream().mapToInt(CancelleryItem::getCost).sum();
    }
}
