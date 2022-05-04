package data_structures.bloom_filter;

public class BloomFilter {
    public int filter_len;
    public int bits;

    private final static int SECOND_HASH_MULTIPLIER = 223;

    public BloomFilter(int f_len) {
        assert f_len > 0;
        filter_len = f_len;
        bits = 0;
        // создаём битовый массив длиной f_len ...
    }

    // хэш-функции
    public int hash1(String str1) {
        assert filter_len > 0;
        // 17
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
             code = (code * 17) + str1.charAt(i);
        }
        // реализация ...
        return Math.abs(code) % filter_len;
    }

    public int hash2(String str1) {
        assert filter_len > 0;
        assert str1 != null;
        // 223
        // реализация ...
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
            code = (code + str1.charAt(i)) * SECOND_HASH_MULTIPLIER;
        }
        return Math.abs(code) % filter_len;
    }

    public void add(String str1) {
        bits = bits | (1 << hash1(str1));
        bits = bits | (1 << hash2(str1));
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1) {
        return ((1 << hash1(str1) | 1 << hash2(str1)) & bits) != 0;
    }
}
