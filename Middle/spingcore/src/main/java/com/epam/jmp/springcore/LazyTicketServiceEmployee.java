package com.epam.jmp.springcore;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class LazyTicketServiceEmployee implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        if (method.getName().equals("workAllDay")) {
            System.out.println("We are closed.");
        }
        return null;
    }
}
