package ooap_second.task_twenty_second;

import java.math.BigDecimal;
import java.util.List;

// НПС могут быть продающими, атакующими, защищающими итд. Упрощяя, их классификации будут ограничены:
// передвиженем, атакой, защитой, продажей
// Но все эти действия будут зависить от их стихиии. Если нпс стихии вода, то предметы воды он покупает дороже, а продает
// дешевле, атакует только этой стихией, получает урон сильнее от других стихий и передвигается бысрее только
// в своей стихии обитания (пр. в воде). Итого если мы будем реализовывать каждый класс наследник на каждый осн подтип
// нпс (атакующий, продающий, защищающий) на каждый элемент - получим декартово произведение. Чтобы упростить я выделил
// осн. характиристику, а побочные (стихия) вынес в переменную класса, также используя полиморфизм.

public class Solution {
    static class NPC {
        protected Element element;

        NPC(Element element) {
            this.element = element;
        }

        void move(Integer environmentId) {
            if (element.id().equals(environmentId)) {
                System.out.println("Moving fast");
                return;
            }
            System.out.println("Moving slowly");
        }
    }

    static class AttackNPC extends NPC {
        AttackNPC(Element element) {
            super(element);
        }

        // Returns attack health points
        BigDecimal attack() {
            return BigDecimal.ONE.multiply(element.abilityIds().stream().findAny().map(BigDecimal::valueOf).orElse(BigDecimal.ONE));
        }
    }

    abstract static class Element {
        abstract List<Integer> abilityIds();
        abstract Integer id();
    }

    static class FireElement extends Element {

        @Override
        List<Integer> abilityIds() {
            return List.of(1, 2, 3);
        }

        @Override
        Integer id() {
            return 1;
        }
    }
}
