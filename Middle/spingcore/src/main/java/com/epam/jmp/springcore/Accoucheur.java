package com.epam.jmp.springcore;

import com.epam.jmp.springcore.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Accoucheur implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (o instanceof Person) {
            System.out.println("BeforeInitialization : " + s);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof Person) {
            System.out.println("AfterInitialization : " + s);
        }
        return o;
    }
}
