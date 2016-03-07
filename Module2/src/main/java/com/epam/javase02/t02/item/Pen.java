package com.epam.javase02.t02.item;

import com.sun.istack.internal.NotNull;

import java.awt.Color;

/**
 * Created by Nick on 04.03.2016.
 */
public class Pen extends CancelleryItem{
    protected static final Color DEFAULT_COLOR = Color.BLUE;
    protected static final int DEFAULT_LINE_WIDTH = 1;

    @NotNull
    private Color incColor;
    @NotNull
    private int lineWidth;

    public Pen(String owner, int cost) {
        this(DEFAULT_COLOR, DEFAULT_ORGANIZATION, 1, cost, owner);
    }

    public Pen(Color incColor, String manufacturer, int lineWidth, int cost, String owner) {
        super(manufacturer, owner, cost);
        this.incColor = incColor;
        this.lineWidth = lineWidth;
    }

    public Pen writeTo(Paper paper, String note) {
        paper.write(note);
        return this;
    }

    public Color getIncColor() {
        return incColor;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pen pen = (Pen) o;

        if (lineWidth != pen.lineWidth) return false;
        return incColor != null ? incColor.equals(pen.incColor) : pen.incColor == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (incColor != null ? incColor.hashCode() : 0);
        result = 31 * result + lineWidth;
        return result;
    }
}

