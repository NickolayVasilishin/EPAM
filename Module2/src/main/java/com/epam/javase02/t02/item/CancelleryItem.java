package com.epam.javase02.t02.item;

import com.epam.javase02.t02.item.Paper;
import com.epam.javase02.t02.preson.Person;

/**
 * Created by Nick on 04.03.2016.
 */
public abstract class CancelleryItem {
    protected static final String DEFAULT_ORGANIZATION = "EPAM";

    protected String manufacturer;
    protected String owner;
    protected int cost;

    public CancelleryItem(String manufacturer, Person owner, int cost) {
        this(manufacturer, owner.getName(), cost);
    }

    public CancelleryItem(String manufacturer, String owner, int cost) {
        this.manufacturer = manufacturer;
        this.owner = owner;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CancelleryItem that = (CancelleryItem) o;

        if (cost != that.cost) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;

    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }
}
