import data_structures.hash_table.HashTable;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HashTableTest {
    @Test
    public void hashFun_Test() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";
        int expectedValue = testString.hashCode() % 19;

        int actualValue = hashTable.hashFun(testString);

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void seekSlot_When_noCollision() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";
        int expectedValue = testString.hashCode() % 19;

        int actualValue = hashTable.seekSlot(testString);

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void seekSlot_When_oneCollision() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";
        int expectedValue = testString.hashCode() % 19 + 3;

        hashTable.put(testString);

        int actualValue = hashTable.seekSlot(testString);

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void seekSlot_When_manyCollisions() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";
        int expectedValue = 2;

        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);

        int actualValue = hashTable.seekSlot(testString);

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void seekSlot_When_noFreeSlot() {
        HashTable hashTable = new HashTable(3, 1);

        String testString = "123";

        int expectedValue = -1;

        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);

        int actualValue = hashTable.seekSlot(testString);

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    public void put_When_isEmpty() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";

        hashTable.put(testString);

        Assertions.assertThat(hashTable.slots[12]).isEqualTo(testString);
    }

    @Test
    public void put_When_oneCollision() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";

        hashTable.put(testString);
        hashTable.put(testString);

        Assertions.assertThat(hashTable.slots[12]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[15]).isEqualTo(testString);
    }

    @Test
    public void put_When_manyCollisions() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";

        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);

        Assertions.assertThat(hashTable.slots[12]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[15]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[18]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[2]).isEqualTo(testString);
    }

    @Test
    public void put_When_noFreeSlot() {
        HashTable hashTable = new HashTable(3, 1);

        String testString = "123";

        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        int actualValue = hashTable.put(testString);

        Assertions.assertThat(hashTable.slots[0]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[1]).isEqualTo(testString);
        Assertions.assertThat(hashTable.slots[2]).isEqualTo(testString);
        Assertions.assertThat(actualValue).isEqualTo(-1);
    }

    @Test
    public void find_When_noCollision() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";

        hashTable.put(testString);

        int actualValue = hashTable.find(testString);

        Assertions.assertThat(actualValue).isEqualTo(12);
    }

    @Test
    public void find_When_noSuchElement() {
        HashTable hashTable = new HashTable(19, 3);

        String testString = "123";

        hashTable.put(testString);

        int actualValue = hashTable.find("321");

        Assertions.assertThat(actualValue).isEqualTo(-1);
    }

    @Test
    public void find_When_manyCollisions() {
        HashTable hashTable = new HashTable(7, 2);

        String testString = "123";

        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put(testString);
        hashTable.put("321");

        int actualValue = hashTable.find("321");

        Assertions.assertThat(actualValue).isEqualTo(3);
    }
}
