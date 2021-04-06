import data_structures.bloom_filter.BloomFilter;

public class Main {
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(32);
        bloomFilter.add("0123456789");
        bloomFilter.add("1234567890");
        bloomFilter.add("2345678901");
        bloomFilter.add("3456789012");
        bloomFilter.add("4567890123");
        bloomFilter.add("5678901234");
        bloomFilter.add("6789012345");
        bloomFilter.add("7890123456");
        bloomFilter.add("8901234567");
        bloomFilter.add("9012345678");

        System.out.println(bloomFilter.isValue("3456789012"));
        System.out.println(bloomFilter.isValue("8901234567"));
        System.out.println(bloomFilter.isValue("55"));

    }
}
