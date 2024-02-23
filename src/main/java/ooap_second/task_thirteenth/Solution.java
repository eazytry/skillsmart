package ooap_second.task_thirteenth;

public class Solution {

    static class General {
        // 1
        public void method1() {}

        protected void method4() {}

        protected void method3() {}
    }

    static class Any extends General {
        @Override
        public void method3() {
            super.method3();
        }
    }

    public static void main(String[] args) {
        var general = new General();
        var any = new Any();
        // 1
        general.method1();
        any.method1();

        // 4 - Метод доступен только для классов-наследников/классов из того же пакет == не доступен для сторонних
        // (клиентских) классов
        general.method4();
        any.method4();

        // 3 - Суперкласс задает более узкую область видимости, но класс-наследник может ее расширять, используя
        // при этом ф-л из метода суперкласса.
        general.method3();
        any.method3();
    }
}
