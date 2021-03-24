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
        powerSet.put("123");

        //than
        assertThat(powerSet.get("123")).isTrue();
        assertThat(powerSet.size()).isEqualTo(1);
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
}
