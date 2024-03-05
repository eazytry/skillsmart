package ooap_second.task_twenty_first;

public class Solution {
    // Implementation inheritance
    static class Animal {
        void move() {
            System.out.println("Moving");
        }
        void eat() {
            System.out.println("Eating");
        }
    }

    static class Dog extends Animal {
        void bark() {
            System.out.println("Barking");
        }
    }

    static class LoggedAnimal extends Dog {
        @Override
        void move() {
            try {
                System.out.println("Log before move");
                super.move();
                System.out.println("Log after move");
            } catch (Exception e) {
                System.out.println("Log when exception thrown");
            }
        }

        @Override
        void eat() {
            try {
                System.out.println("Log before eat");
            super.eat();
                System.out.println("Log after eat");
            } catch (Exception e) {
                System.out.println("Log when exception thrown");
            }
        }

        @Override
        void bark() {
            try {
                System.out.println("Log before bark");
            super.bark();
                System.out.println("Log after bark");
            } catch (Exception e) {
                System.out.println("Log when exception thrown");
            }
        }
    }

    // facility inheritance
    static class Scenario {
        void walk() {
            System.out.println("Walk");
        }
        void kill() {
            System.out.println("Kill");
        }
        void call() {
            System.out.println("Call");
        }
        void love() {
            System.out.println("Love");
        }
    }

    static class PositiveScenario extends Scenario {
        void positiveScenario() {
            walk();
            call();
            love();
        }
    }

    static class NegativeScenario extends Scenario {
        void negativeScenario() {
            walk();
            kill();
            call();
        }
    }
}
