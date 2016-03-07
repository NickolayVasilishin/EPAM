package com.epam.javase02.t01;

import com.epam.javase02.t02.item.Pen;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.print.Paper;

/**
 * Created by Nick on 04.03.2016.
 */
public class MyPen extends Pen{

    public MyPen() {
        this(DEFAULT_COLOR, DEFAULT_ORGANIZATION, 1);
    }

   public MyPen(Color color, String manufacturer, int cost) {
       super(color, manufacturer, DEFAULT_LINE_WIDTH, cost, DEFAULT_ORGANIZATION);
   }


}
