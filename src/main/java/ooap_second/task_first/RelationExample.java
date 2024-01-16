package ooap_second.task_first;

import java.util.List;

import static java.lang.System.out;

public class RelationExample {
    public static void main(String[] args) {
        var car = new Car();
        var airplane = new Airplane();
        var helicopter = new Helicopter();

        // Полиморфизм
        var transports = List.of(car, airplane, helicopter);

        // Будут выведены разные типы классов, хотя стрим работает с классом Transport
        transports.stream().map(Transport::toString).forEach(out::println);
    }

    static class Transport {
        // Композиция
        private List<Part> parts;

        @Override
        public String toString() {
            return getClass().getSimpleName();
        }
    }

    // Наследование
    static class AirTransport extends Transport {
    }

    // Наследование
    static class GroundTransport extends Transport {
    }

    // Множественное наследование
    static class Car extends GroundTransport {
    }

    // Множественное наследование
    static class Airplane extends AirTransport {
    }

    // Множественное наследование
    static class Helicopter extends AirTransport {
    }

    static class Part {
    }
}
