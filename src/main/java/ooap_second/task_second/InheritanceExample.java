package ooap_second.task_second;

import java.math.BigDecimal;

public class InheritanceExample {
    // Пример, когда дочерний класс делает более общим суперкласс

    // Калькулятор, который работает только с отрицательными числами
    static class RestrictedCalculator {
        public BigDecimal add(BigDecimal number1, BigDecimal number2) {
            if (number1.compareTo(BigDecimal.ZERO) < 0 || number2.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Не работает с негативными числами");
            }
            return number1.add(number2);
        }
    }

    // Калькулятор, который "ослябляет" ограничение на отррицательные числа
    static class GeneralCalculator extends RestrictedCalculator {
        @Override
        public BigDecimal add(BigDecimal number1, BigDecimal number2) {
            return number1.add(number2);
        }
    }

    // Пример, когда дочерний класс делает более узким суперкласс

    // Корабль, может быть использован для разных вещей
    static class Ship {
        public void move() {
            System.out.println("Moving the ship through the world.");
        }
    }

    // Корабль для перевозки контейнеров, не может быть использовать для транспортировки людей или круизов
    static class CargoShip extends Ship {
        @Override
        public void move() {
            System.out.println("Moving the ship with containers, it can't be used for tourism or people transfer");
        }
    }

    // Рефлексия:
    // На мой взгляд, пример, где дочерний класс расширяет область применения суперкласса, противоречит принципу
    // Лисков и представляет собой НЕочевидное поведение, использование такого подхода требует особой аккуратности
    // и предварителнього тщательного анализа целесообразности. На текущий момент я не могу придумать случай из реаль-
    // ной практики, где бы мне мог пригодиться такой подход.
}
