package ooap_second.task_sixteenth;

import java.util.function.Function;
import java.util.stream.Stream;

public class Solution {
    static class Fruit {
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class Apple extends Fruit {
        @Override
        public String toString() {
            return "Apple";
        }
    }

    static class Banana extends Fruit {
        @Override
        public String toString() {
            return "Banana";
        }
    }

    static <T extends Fruit> Function<T, String> getFruitString() {
        return Fruit::toString;
    }

    public static void main(String[] args) {
        Stream.of(new Banana(), new Apple())
                .map(getFruitString())
                .forEach(System.out::println);
    }
}
