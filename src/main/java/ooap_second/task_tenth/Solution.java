package ooap_second.task_tenth;

public class Solution {
    static class FirstMethod {
        // Ключевое слово private не позволяет переопределить метод для классов-потомков, но и не позволяет использовать
        private void test() {}
    }


    static class SecondMethod {
        // Ключевое слово final запрещает наследовать метод классам-потомкам
        private final void test() {}
    }
}
