package data_structures.bloom_filter;

public class BloomFilter {
    public int filter_len;
    public int bits;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        bits = 0;
        // создаём битовый массив длиной f_len ...
    }

    // хэш-функции
    public int hash1(String str1) {
        // 17
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
             code = (code * 17) + str1.charAt(i);
        }
        // реализация ...
        return Math.abs(code) % filter_len;
    }

    public int hash2(String str1) {
        // 223
        // реализация ...
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
            code = (code + str1.charAt(i)) * 223;
        }
        return Math.abs(code) % filter_len;
    }

    public void add(String str1) {
        bits = bits | (1 << hash1(str1));
        bits = bits | (1 << hash2(str1));
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1) {
        System.out.println(Integer.toBinaryString(bits));
        return ((1 << hash1(str1) | 1 << hash2(str1)) & bits) != 0;
    }
}