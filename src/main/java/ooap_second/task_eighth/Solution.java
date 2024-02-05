package ooap_second.task_eighth;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        // Ковариантность
        Object[] objects = new String[]{"1", "2", "3"};

        List<? extends Number> numbers = List.of(1L, 4, 2F, 10D);

        // Контравариантность
        List<? super IllegalStateException> superIllegalStateExceptions =
                List.of(new RuntimeException(), new Throwable(), new Error());
    }
}
