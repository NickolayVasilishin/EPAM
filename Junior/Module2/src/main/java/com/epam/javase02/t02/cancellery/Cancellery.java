package com.epam.javase02.t02.cancellery;

import com.epam.javase02.t02.item.CancelleryItem;
import com.epam.javase02.t02.person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 04.03.2016.
 *
 * This class provides simple employee management functionality.
 */
public class Cancellery {
    private Map<String, Person> employees;

    public Cancellery() {
        employees = new HashMap<>();
    }

    /**
     * Registers a person as an employee.
     * @param person
     * @return - this instance.
     */
    public Cancellery registerEmployee(Person person) {
        employees.put(person.getName(), person);
        return this;
    }

    /**
     * Calculates cost of all person's cancellery.
     * @param person
     * @return - cost of person's cancellery or -1 if person isn't registered.
     */
    public int calculateCost(Person person) {
        if (employees.containsKey(person.getName())) {
            return employees.get(person.getName()).showItems().stream().mapToInt(CancelleryItem::getCost).sum();
        } else {
            return -1;
        }
    }
}
