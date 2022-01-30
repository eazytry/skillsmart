package cache;

import data_structures.cache.NativeCache;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CacheTest {
    @Test
    public void get_IsNotExist() {
        NativeCache<String> cache = new NativeCache<>(5);

        Assertions.assertNull(cache.get("test"));
    }

    @Test
    public void get_Exist() {
        NativeCache<String> cache = new NativeCache<>(5);

        cache.put("test", "value");

        Assertions.assertEquals("value", cache.get("test"));
    }

    @Test
    public void put_Exist() {
        NativeCache<String> cache = new NativeCache<>(5);

        cache.put("test", "value");
        cache.put("test", "value2");

        Assertions.assertEquals("value2", cache.get("test"));
    }

    @Test
    public void put_NotExist() {
        NativeCache<String> cache = new NativeCache<>(5);

        cache.put("test", "value");

        Assertions.assertEquals("value", cache.get("test"));
    }


    @Test
    public void put_CacheFull() {
        NativeCache<String> cache = new NativeCache<>(3);

        cache.put("test", "value");
        cache.put("test1", "value");
        cache.put("test2", "value");

        cache.get("test");
        cache.get("test1");

        cache.put("test3", "value");

        Assertions.assertNotNull(cache.get("test"));
        Assertions.assertNotNull(cache.get("test1"));
        Assertions.assertNotNull(cache.get("test3"));
        Assertions.assertNull(cache.get("test2"));
    }
    @Test
    public void put_FullCollisions() {
        NativeCache<String> cache = new NativeCache<>(5);

        cache.put("1", "value");
        cache.put("6", "value");
        cache.put("12", "value");
        cache.put("17", "value");
        cache.put("26", "value");

        cache.put("17", "value2");

        Assertions.assertEquals(cache.get("17"), "value2");
    }

}

