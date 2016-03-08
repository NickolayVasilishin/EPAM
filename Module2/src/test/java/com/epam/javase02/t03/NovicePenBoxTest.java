package com.epam.javase02.t03;

import com.epam.javase02.t02.person.Person;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
/**
 * Created by Nick on 07.03.2016.
 */
public class NovicePenBoxTest {
    @Test
    public void createNovicePenBox() {
        Person novice = new Person("Newbie", new NewbieCancellerySet());
        assertNotNull(novice.showItems());
        assertEquals(novice.showItems().get(0), new NewbieCancellerySet().get().get(0));
    }
}
