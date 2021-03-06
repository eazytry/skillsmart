import data_structures.power_set.PowerSet;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PowerSetTest {
    @Test
    public void testDefaultConstructor() {
        PowerSet powerSet = new PowerSet();

        assertThat(powerSet.size()).isEqualTo(0);
    }

    @Test
    public void put_When_empty_Than_getIsTrue() {
        //when
        PowerSet powerSet = new PowerSet();
        powerSet.put("123");

        //than
        assertThat(powerSet.get("123")).isTrue();
    }

    @Test
    public void put_When_alreadyContains_Than_getIsTrue_And_sizeNotChanged() {
        //when
        PowerSet powerSet = new PowerSet();
        powerSet.put("123");
        powerSet.put("13");
        powerSet.put("3");
        powerSet.put("23");
        powerSet.put("123");

        //than
        assertThat(powerSet.get("123")).isTrue();
        assertThat(powerSet.size()).isEqualTo(4);
    }

    @Test
    public void put_When_valuesCountIs20_000_Than_sizeIs20_000() {
        //when
        PowerSet powerSet = new PowerSet();
        for (int i = 0; i < 20000; i++) {
            powerSet.put(String.valueOf(i));
        }
        //than
        assertThat(powerSet.size()).isEqualTo(20_000);
    }

    @Test
    public void remove_When_empty_Than_False() {
        //when
        PowerSet powerSet = new PowerSet();

        //than
        assertThat(powerSet.remove("123")).isFalse();
    }

    @Test
    public void remove_When_notEmpty_And_notContains_Than_False() {
        //when
        PowerSet powerSet = new PowerSet();
        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        //than
        assertThat(powerSet.remove("123")).isFalse();
        assertThat(powerSet.size).isEqualTo(3);
    }

    @Test
    public void remove_When_notEmpty_And_contains_Than_true_And_sizeDecrement() {
        //when
        PowerSet powerSet = new PowerSet();
        powerSet.put("1");
        powerSet.put("2");
        powerSet.put("3");

        //than
        assertThat(powerSet.remove("1")).isTrue();
        assertThat(powerSet.size).isEqualTo(2);
    }

    @Test
    public void intersection_When_empty_Than_empty() {
        //when
        PowerSet powerSet = new PowerSet();

        //than
        assertThat(powerSet.intersection(new PowerSet()).size()).isEqualTo(0);
    }

    @Test
    public void intersection_When_notEmpty_And_noOneContains_Than_empty() {
        //when
        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put("1");
        firstPowerSet.put("2");
        firstPowerSet.put("3");

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put("4");
        secondPowerSet.put("5");
        secondPowerSet.put("6");

        //than
        assertThat(firstPowerSet.intersection(secondPowerSet).size()).isEqualTo(0);
    }

    @Test
    public void intersection_When_notEmpty_And_contains_Than_intersectionContainsInBoth_And_differentIsNotContainsInOther() {
        //when
        String firstIntersectionVal = "3";
        String secondIntersectionVal = "2";
        String firstDifferentVal = "1";
        String secondDifferentVal = "4";

        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put(firstDifferentVal);
        firstPowerSet.put(firstIntersectionVal);
        firstPowerSet.put(secondIntersectionVal);

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(secondDifferentVal);
        secondPowerSet.put(firstIntersectionVal);
        secondPowerSet.put(secondIntersectionVal);

        //than
        PowerSet actualPowerSet = firstPowerSet.intersection(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(2);
        assertThat(actualPowerSet.get(firstIntersectionVal)).isTrue();
        assertThat(actualPowerSet.get(secondIntersectionVal)).isTrue();
        assertThat(secondPowerSet.get(firstDifferentVal)).isFalse();
        assertThat(firstPowerSet.get(secondDifferentVal)).isFalse();
    }

    @Test
    public void union_When_bothNotEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        String firstVal = "1";
        String secondVal = "2";
        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put(firstVal);
        firstPowerSet.put(secondVal);
        firstPowerSet.put(thirdVal);
        firstPowerSet.put(fourthVal);

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        PowerSet actualPowerSet = firstPowerSet.union(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(7);
        assertThat(actualPowerSet.get(firstVal)).isTrue();
        assertThat(actualPowerSet.get(secondVal)).isTrue();
        assertThat(actualPowerSet.get(thirdVal)).isTrue();
        assertThat(actualPowerSet.get(fourthVal)).isTrue();
        assertThat(actualPowerSet.get(fifthVal)).isTrue();
        assertThat(actualPowerSet.get(sixthVal)).isTrue();
        assertThat(actualPowerSet.get(seventhVal)).isTrue();
    }

    @Test
    public void union_When_oneIsEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        PowerSet actualPowerSet = firstPowerSet.union(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(5);
        assertThat(actualPowerSet.get(thirdVal)).isTrue();
        assertThat(actualPowerSet.get(fourthVal)).isTrue();
        assertThat(actualPowerSet.get(fifthVal)).isTrue();
        assertThat(actualPowerSet.get(sixthVal)).isTrue();
        assertThat(actualPowerSet.get(seventhVal)).isTrue();
    }

    @Test
    public void union_When_bothEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        PowerSet firstPowerSet = new PowerSet();
        PowerSet secondPowerSet = new PowerSet();

        //than
        PowerSet actualPowerSet = firstPowerSet.union(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(0);
    }



    @Test
    public void difference_When_bothNotEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        String firstVal = "1";
        String secondVal = "2";
        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put(firstVal);
        firstPowerSet.put(secondVal);
        firstPowerSet.put(thirdVal);
        firstPowerSet.put(fourthVal);

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        PowerSet actualPowerSet = firstPowerSet.difference(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(2);
        assertThat(actualPowerSet.get(firstVal)).isTrue();
        assertThat(actualPowerSet.get(secondVal)).isTrue();
        assertThat(actualPowerSet.get(thirdVal)).isFalse();
        assertThat(actualPowerSet.get(fourthVal)).isFalse();
        assertThat(actualPowerSet.get(fifthVal)).isFalse();
        assertThat(actualPowerSet.get(sixthVal)).isFalse();
        assertThat(actualPowerSet.get(seventhVal)).isFalse();
    }



    @Test
    public void difference_When_oneIsEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        PowerSet actualPowerSet = secondPowerSet.difference(firstPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(5);
        assertThat(actualPowerSet.get(thirdVal)).isTrue();
        assertThat(actualPowerSet.get(fourthVal)).isTrue();
        assertThat(actualPowerSet.get(fifthVal)).isTrue();
        assertThat(actualPowerSet.get(sixthVal)).isTrue();
        assertThat(actualPowerSet.get(seventhVal)).isTrue();
    }

    @Test
    public void difference_When_bothEmpty_Than_valuesIsCorrect_And_sizeIsCorrect() {
        //when
        PowerSet firstPowerSet = new PowerSet();
        PowerSet secondPowerSet = new PowerSet();

        //than
        PowerSet actualPowerSet = firstPowerSet.difference(secondPowerSet);

        assertThat(actualPowerSet.size()).isEqualTo(0);
    }

    @Test
    public void subset_When_bothAreNotEmpty_Than_true() {
        //when

        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put(thirdVal);
        firstPowerSet.put(fourthVal);

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        assertThat(secondPowerSet.isSubset(firstPowerSet)).isTrue();
    }

    @Test
    public void subset_When_passedSetIsEmpty_Than_true() {
        //when

        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        assertThat(secondPowerSet.isSubset(firstPowerSet)).isTrue();
    }

    @Test
    public void subset_When_bothSetsAreEmpty_Than_true() {
        //when
        PowerSet firstPowerSet = new PowerSet();

        PowerSet secondPowerSet = new PowerSet();

        //than
        assertThat(secondPowerSet.isSubset(firstPowerSet)).isTrue();
    }

    @Test
    public void subset_When_passedSetIsNotSubset_Than_false() {
        String firstVal = "1";
        String secondVal = "2";
        String thirdVal = "3";
        String fourthVal = "4";
        String fifthVal = "5";
        String sixthVal = "6";
        String seventhVal = "7";

        PowerSet firstPowerSet = new PowerSet();
        firstPowerSet.put(firstVal);
        firstPowerSet.put(secondVal);

        PowerSet secondPowerSet = new PowerSet();
        secondPowerSet.put(thirdVal);
        secondPowerSet.put(fourthVal);
        secondPowerSet.put(fifthVal);
        secondPowerSet.put(sixthVal);
        secondPowerSet.put(seventhVal);

        //than
        assertThat(secondPowerSet.isSubset(firstPowerSet)).isFalse();
    }
}
