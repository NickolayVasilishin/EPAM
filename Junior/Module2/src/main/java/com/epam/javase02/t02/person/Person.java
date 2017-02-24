package com.epam.javase02.t02.person;

import com.epam.javase02.t02.item.CancelleryItem;
import com.epam.javase02.t03.NewbieCancellerySet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 04.03.2016.
 */
public class Person {
    private String name;
    private List<CancelleryItem> items;

    public Person(String name) {
        this.name = name;
        items = new LinkedList();
    }

    public Person(String name, NewbieCancellerySet set) {
        this.name = name;
        items = set.get();
    }

    public String getName() {
        return name;
    }

    public List<CancelleryItem> showItems() {
        return Collections.unmodifiableList(items);
    }

    public Person addItem(CancelleryItem item) {
        this.items.add(item);
        return this;
    }

    public Person addItem(CancelleryItem item, int count) {
        while(count-- > 0)
            this.items.add(item);
        return this;
    }

    public CancelleryItem getItem(CancelleryItem item) {
        CancelleryItem i = items.get(items.indexOf(item));
        items.remove(item);
        return i;
    }

    /**
     * Not very useful because you have to cast returned list to needed type.
     * @param clazz - class is used as name or type of cancellery item.
     * @return list of CancelleryItem's which have to be casted.
     */
    public List<CancelleryItem> getAllOf(Class<? extends CancelleryItem> clazz) {
        final LinkedList<CancelleryItem> foundItems = new LinkedList<>();
        items.stream().filter(cancelleryItem -> cancelleryItem.getClass().equals(clazz)).forEach(foundItems::add);
        items.removeAll(foundItems);
        return foundItems;
    }

}
