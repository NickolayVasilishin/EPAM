package com.epam.javase02.t03;

import com.epam.javase02.t02.item.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 07.03.2016.
 *
 * Represents simple set of cancellery items.
 */
public class NewbieCancellerySet {
    private List<CancelleryItem> items;
    
    public NewbieCancellerySet() {
        items = new LinkedList<>();
        items.add(new Clip("clipCo", "Newbie", 2));
        items.add(new Paper("papersCo", "Newbie", 1));
        items.add(new Pen("Newbie", 20));
        items.add(new Eraser("Erasers", "Newbie", 12));
    }

    public List<CancelleryItem> get() {
        return items;
    }
}
