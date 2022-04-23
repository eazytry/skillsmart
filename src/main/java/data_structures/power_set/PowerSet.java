package data_structures.power_set;

import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PowerSet {
    public static final int DEFAULT_ARRAY_CAPACITY = 20_000;

    public int size;
    public String[] values;

    public PowerSet() {
        initDefaultFields();
    }

    private PowerSet(Object[] values) {
        initDefaultFields();
        for (Object val : Arrays.stream(values).filter(v -> !Objects.isNull(v)).toArray())
            put((String)val);
    }

    private void initDefaultFields() {
        this.size = 0;
        this.values = new String[DEFAULT_ARRAY_CAPACITY];
    }

    public int size() {
        return this.size;
    }


    public void put(String value) {
        // find null or equals value insert index
        OptionalInt insertionIndexCandidateOpt = Arrays.stream(searchIndexSequence(value))
                .filter(i -> isNullOrEquals(value, values[i]))
                .limit(1L)
                .findFirst();
        if (insertionIndexCandidateOpt.isPresent() && values[insertionIndexCandidateOpt.getAsInt()] == null) {
            values[insertionIndexCandidateOpt.getAsInt()] = value;
            size++;
        }
    }

    public boolean get(String value) {
        if (value == null)
            return false;
        return Arrays.stream(searchIndexSequence(value)).anyMatch(i -> value.equals(values[i]));
    }

    public boolean remove(String value) {
        OptionalInt valueIndexOpt = Arrays.stream(searchIndexSequence(value))
                .filter(i -> value.equals(values[i]))
                .findFirst();
        if (valueIndexOpt.isEmpty()) {
            return false;
        }
        values[valueIndexOpt.getAsInt()] = null;
        size--;
        return true;
    }

    public PowerSet intersection(PowerSet set2) {
        return new PowerSet(Arrays.stream(values).filter(set2::get).toArray());
    }

    public PowerSet union(PowerSet set2) {
        return new PowerSet(Stream.concat(Arrays.stream(values), Arrays.stream(set2.values)).toArray());
    }

    public PowerSet difference(PowerSet set2) {
        return new PowerSet(Arrays.stream(values).filter(val -> !set2.get(val)).toArray());
    }

    public boolean isSubset(PowerSet set2) {
        return Arrays.stream(set2.values).filter(v -> !Objects.isNull(v)).allMatch(this::get);
    }

    private int calcIndex(String value) {
        return Math.abs(value.hashCode()) % values.length;
    }

    private int[] searchIndexSequence(String value) {
        return IntStream.concat(IntStream.range(calcIndex(value), values.length), IntStream.range(0, calcIndex(value))).toArray();
    }

    /**
     * @param firstValue  value to compare
     * @param secondValue value to compare
     * @param <T>         type of values
     * @return the result of equals() or false, if one of params is null
     * @see #equals(Object)
     */
    private static <T> boolean isNullOrEquals(T firstValue, T secondValue) {
        return Objects.isNull(firstValue) || Objects.isNull(secondValue) || firstValue.equals(secondValue);
    }
}
