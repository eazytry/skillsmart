package data_structures.bloom_filter;

public class BloomFilter {
    public int filter_len;
    public boolean[] bitArr;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        bitArr = new boolean[f_len];
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
        return Math.abs(code) % bitArr.length;
    }

    public int hash2(String str1) {
        // 223
        // реализация ...
        int code = 0;
        for (int i = 0; i < str1.length(); i++) {
            code = (code + str1.charAt(i)) * 223;
        }
        return Math.abs(code) % bitArr.length;
    }

    public void add(String str1) {
        bitArr[hash1(str1)] = true;
        bitArr[hash2(str1)] = true;
        // добавляем строку str1 в фильтр
    }

    public boolean isValue(String str1) {
        return bitArr[hash1(str1)] && bitArr[hash1(str1)];
    }
}