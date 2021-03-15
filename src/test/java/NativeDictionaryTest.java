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
    public void isKey_When_overrideKey() {
        NativeDictionary<Integer> nativeDictionary = new NativeDictionary<>(10, Integer.class);

        nativeDictionary.put("3", 21313);
        nativeDictionary.put("2sdfasdf3", 21313);
        nativeDictionary.put("5reteatger", 21313);
        nativeDictionary.put("344564562340", 21313);

        Assertions.assertThat(nativeDictionary.isKey("1")).isFalse();
        Assertions.assertThat(nativeDictionary.isKey("344564562340")).isTrue();
    }

    @Test
    public void isKey_When_integerKeyNotContainsInNotEmpty() {
        NativeDictionary<Integer> nd = new NativeDictionary<>(97, Integer.class);

        nd.put("0123456789", 123456789);
        nd.put("2", 123456789);
        nd.put("200", 123456789);
        nd.put("200000", 123456789);
        nd.put("sdfgadsfg", 123456789);
        nd.put("aertgrdfvxc", 123456789);
        nd.put("aertgrdfvxcfsadefdaa", 123456789);

        Assertions.assertThat(nd.isKey("1234567890")).isFalse();
        Assertions.assertThat(nd.isKey("0123456789")).isTrue();
        Assertions.assertThat(nd.isKey("2")).isTrue();
        Assertions.assertThat(nd.isKey("200")).isTrue();
        Assertions.assertThat(nd.isKey("200000")).isTrue();
        Assertions.assertThat(nd.isKey("sdfgadsfg")).isTrue();
        Assertions.assertThat(nd.isKey("aertgrdfvxc")).isTrue();
        Assertions.assertThat(nd.isKey("aertgrdfvxcfsadefdaa")).isTrue();
    }

    @Test
    public void isKey_When_contains() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(10, String.class);

        nativeDictionary.put("1", "123");
        nativeDictionary.put("2", "123");
        nativeDictionary.put("124234234", "123");
        nativeDictionary.put("1sdgdsafg", "123");
        nativeDictionary.put("ASDasd1", "123");
        nativeDictionary.put("1asdasd", "123");

        Assertions.assertThat(nativeDictionary.isKey("1")).isTrue();
    }

    @Test
    public void isKey_When_containsAndCollision() {
        NativeDictionary<String> nativeDictionary = new NativeDictionary<>(2, String.class);

        nativeDictionary.put("1", "123");
        nativeDictionary.put("3", "123");
        nativeDictionary.put("sdfsdfsad", "123");
        nativeDictionary.put("ASDFDSAF", "123");
        nativeDictionary.put("sdff", "123");
        nativeDictionary.put("sdfsdfsdf", "123");
        nativeDictionary.put("23142354", "123");

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

        Assertions.assertThat(nativeDictionary.isKey(expectedKey)).isTrue();
        Assertions.assertThat(nativeDictionary.isKey(collisionExpectedKey)).isTrue();
    }
}
