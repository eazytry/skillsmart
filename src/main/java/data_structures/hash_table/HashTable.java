package data_structures.hash_table;

public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        return value.hashCode() % size;
    }

    public int seekSlot(String value) {
        // находит индекс пустого слота для значения, или -1
        int index = hashFun(value);
        int currIndex = index;
        do {
            if (slots[currIndex] == null) {
                return currIndex;
            }
            currIndex = (currIndex + this.step) < size ? currIndex + this.step : this.step - (size - currIndex);
        } while (currIndex != index);
        return -1;
    }

    public int put(String value) {
        // записываем значение по хэш-функции

        // возвращается индекс слота или -1
        // если из-за коллизий элемент не удаётся разместить
        int index = seekSlot(value);
        if (index >= 0)
            slots[index] = value;
        return index;
    }

    public int find(String value) {
        // находит индекс слота со значением, или -1
        int index = hashFun(value);
        int currIndex = index;
        do {
            if (slots[currIndex] == null)
                return -1;
            if (slots[currIndex].equals(value)) {
                return currIndex;
            }
            currIndex = (currIndex + this.step) < size ? currIndex + this.step : this.step - (size - currIndex);
        } while (currIndex != index);
        return -1;
    }
}