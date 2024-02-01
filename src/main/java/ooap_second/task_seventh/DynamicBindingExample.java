package ooap_second.task_seventh;

public class DynamicBindingExample {
    interface Animal {
        void sound();
    }

    static class Dog implements Animal {
        @Override
        public void sound() {
            System.out.println("Bark");
        }
    }

    static class Cat implements Animal {
        @Override
        public void sound() {
            System.out.println("Meow");
        }
    }

    public static void main(String[] args) {
        // Создаем переменные типа Animal
        Animal dog = new Dog();
        Animal cat = new Cat();

        // Метод, используемый в каждой из переменных будет выбран динамически во время выполнения
        dog.sound();
        cat.sound();
    }
}
