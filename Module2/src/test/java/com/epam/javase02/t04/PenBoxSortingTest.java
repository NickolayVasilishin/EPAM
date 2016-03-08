package com.epam.javase02.t04;

import com.epam.javase02.t03.NewbieCancellerySet;
import org.junit.Test;

/**
 * Created by Nick on 07.03.2016.
 */
public class PenBoxSortingTest {

    @Test
    public void sort() {
        NewbieCancellerySet set = new NewbieCancellerySet();
        set.get().sort((o1, o2) -> o1.getCost() - o2.getCost());
        System.out.println(set.get());
        set.get().sort((o1, o2) -> o1.getClass().toString().compareTo(o2.getClass().toString()));
        System.out.println(set.get());
        set.get().sort((o1, o2) -> {
            int result = 0;
            result = o1.getCost() - o2.getCost();
            if (result == 0) {
                result = o1.getClass().toString().compareTo(o2.getClass().toString());
            }
            return result;
        });
        System.out.println(set.get());
    }

}
