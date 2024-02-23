package ooap_second.task_twelveth;

import java.io.Serializable;
import java.util.Optional;

public class Solution {
    static class General implements Serializable {
        <T> Optional<T> tryCast(Class<T> targetClass) {
            if (targetClass != null && targetClass.isAssignableFrom(this.getClass())) {
                return Optional.of((T)this);
            }
            return Optional.empty();
        }
    }

    static class Any extends General { }

    static final class None extends Any /*A, B, ....*/ { }
}
