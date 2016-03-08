package com.epam.javase02.t02.item;

/**
 * Created by Nick on 04.03.2016.
 *
 * Only eraser can erase notes on paper.
 */
public class Eraser extends CancelleryItem {

    public Eraser(String manufacturer, String owner, int cost) {
        super(manufacturer, owner, cost);
    }

    public Eraser erase(Paper paper, String string) {
        paper.erase(string);
        return this;
    }

    @Override
    public String toString() {
        return super.toString("");
    }
}
