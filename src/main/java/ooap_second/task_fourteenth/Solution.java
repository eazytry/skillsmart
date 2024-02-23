package ooap_second.task_fourteenth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

public class Solution {
    static abstract class General {
        // Стандартная реализация сложения хешкодов.
        public abstract General add(General general);

        <T> Optional<T> tryCast(Class<T> targetClass) {
            if (targetClass != null && targetClass.isAssignableFrom(this.getClass())) {
                return Optional.of((T)this);
            }
            return Optional.empty();
        }
    }

    static class Vector<T extends General> extends General {
        private List<General> vector;

        public Vector(List<T> list) {
            this.vector = (List<General>) list;
        }

        @Override
        public General add(General general) {
            var vectorOpt = general.tryCast(this.getClass());
            if (vectorOpt.isEmpty() || vectorOpt.get().getVector().size() != this.vector.size()) {
                return null;
            }
            var targetVector = vectorOpt.get().getVector();
            for (int i = 0; i < targetVector.size(); i++) {
                var value = (General) targetVector.get(i);
                this.vector.set(i, this.vector.get(i).add(value));
            }

            return this;
        }

        public List<? extends General> getVector() {
            return vector;
        }

        @Override
        public String toString() {
            return "Vector{" +
                    "vector=" + vector +
                    '}';
        }
    }

    static class Number extends General {
        private BigDecimal number;

        public Number(BigDecimal number) {
            this.number = number;
        }

        @Override
        public General add(General general) {
            var numberOpt = general.tryCast(this.getClass());
            return numberOpt
                    .map(t -> new Number(number.add(t.getNumber())))
                    .orElse(null);
        }

        public BigDecimal getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "number=" + number +
                    '}';
        }
    }

    public static void main(String[] args) {
        var nums1 = new Vector<>(new ArrayList<>(List.of(new Number(ONE), new Number(TEN), new Number(ONE))));
        var nums2 = new Vector<>(new ArrayList<>(List.of(new Number(ZERO), new Number(ONE), new Number(ONE))));
        var nums3 = new Vector<>(new ArrayList<>(List.of(new Number(ZERO), new Number(ZERO), new Number(TEN))));

        var vecs1 = new Vector<>(new ArrayList<>(List.of(nums1, nums2)));
        var vecs2 = new Vector<>(new ArrayList<>(List.of(nums3, nums1)));


        var vecsVecs1 = new Vector<>(new ArrayList<>(List.of(vecs1)));
        var vecsVecs2 = new Vector<>(new ArrayList<>(List.of(vecs2)));

        var sum = vecsVecs1.add(vecsVecs2);

        System.out.println(sum);
    }
}
