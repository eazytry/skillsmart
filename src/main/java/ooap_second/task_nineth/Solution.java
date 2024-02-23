package ooap_second.task_nineth;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import java.util.Objects;
import sun.reflect.ReflectionFactory;

public class Solution {
    abstract class General implements Serializable {

        public void copyTo(General target) throws NoSuchFieldException, IllegalAccessException {
            if (target == null || !this.getClass().equals(target.getClass())) {
                return;
            }
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(target, field.get(this));
            }
        }

        public boolean compare(General target) throws NoSuchFieldException, IllegalAccessException {
            if (target == null || !this.getClass().equals(target.getClass())) {
                return false;
            }
            var isEqual = true;
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType().isPrimitive()) {
                    isEqual = field.get(this) == field.get(target);
                } else {
                    isEqual = Objects.equals(field.get(this), field.get(target));
                }
            }
            return isEqual;
        }

        String print() {
            var fields = this.getClass().getFields();
            var sb = new StringBuilder();
            sb.append("Class type ")
                    .append(this.getClass())
                    .append("\n");
            for (Field field : fields) {
                field.setAccessible(true);
                sb.append(field.getName()).append(" ").append(field).append("\n");
            }
            return sb.toString();
        }

        public boolean isTypeOf(General general) {
            return this.getClass().equals(general.getClass());
        }

    }
}
