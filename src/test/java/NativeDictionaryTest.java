import data_structures.native_dictionary.NativeDictionary;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class NativeDictionaryTest {
    @Test
    public void isKey_When_notContains() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(10, String.class);

        Assertions.assertThat(nativeDictionary.isKey("1")).isFalse();
    }


    @Test
    public void isKey_When_notContainsInNotEmpty() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(10, String.class);

        nativeDictionary.put("3", "123");

        Assertions.assertThat(nativeDictionary.isKey("1")).isFalse();
    }

    @Test
    public void isKey_When_integerKeyNotContainsInNotEmpty() {
        NativeDictionary<Integer> nd = new NativeDictionary<Integer>(97, Integer.class);
        nd.put("0123456789", 123456789);
        nd.isKey("1234567890");
    }

    @Test
    public void isKey_When_contains() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(10, String.class);

        nativeDictionary.put("1", "123");

        Assertions.assertThat(nativeDictionary.isKey("1")).isTrue();
    }

    @Test
    public void isKey_When_containsAndCollision() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(2, String.class);

        nativeDictionary.put("1", "123");
        nativeDictionary.put("3", "123");

        Assertions.assertThat(nativeDictionary.isKey("1")).isTrue();
        Assertions.assertThat(nativeDictionary.isKey("3")).isTrue();
    }

    @Test
    public void put_When_empty() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(2, String.class);

        String expectedKey = "1";
        String expectedValue = "123";
        nativeDictionary.put(expectedKey, expectedValue);

        Assertions.assertThat(nativeDictionary.slots[1]).isEqualTo(expectedKey);
        Assertions.assertThat(nativeDictionary.values[1]).isEqualTo(expectedValue);
    }

    @Test
    public void put_When_collision() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(2, String.class);

        String expectedKey = "1";
        String collisionExpectedKey = "3";
        String expectedValue = "123";

        nativeDictionary.put(expectedKey, expectedValue);
        nativeDictionary.put(collisionExpectedKey, expectedValue);

        Assertions.assertThat(nativeDictionary.slots[0]).isEqualTo(expectedKey);
        Assertions.assertThat(nativeDictionary.values[0]).isEqualTo(expectedValue);
        Assertions.assertThat(nativeDictionary.slots[1]).isEqualTo(collisionExpectedKey);
        Assertions.assertThat(nativeDictionary.values[1]).isEqualTo(expectedValue);
    }
}
