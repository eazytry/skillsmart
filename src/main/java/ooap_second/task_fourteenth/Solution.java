package ooap_second.task_fourteenth;

// Не сразу понял что имелось ввиду в задании
public class Solution {
    public static class Any {}

    public static class Adder<T> extends Any {

        public T sum(T first, T second){

            T ans = null;

            if(first instanceof String){
                ans = sumString(first, second);
            }

            if(first instanceof Integer){
                ans = sumInteger(first, second);
            }

            if (first instanceof Double){
                ans = sumDouble(first, second);
            }

            return ans;
        }

        private T sumString(T first, T second){
            return (T)(first + (String)second);
        }

        private T sumInteger(T first, T second){
            Integer sum = (Integer)first + (Integer)second;
            T value = (T)sum;
            return value;
        }

        private T sumDouble(T first, T second){
            return (T)((Double)((Double)(first) + (Double)second));
        }
    }

    public static class Vector<T> extends Adder<T> {

        public static int ADD_NIL = 0;
        public static int ADD_OK = 1;
        public static int ADD_ERR = 2;

        private int length;
        private T[] arr;
        private int add_status;

        public Vector(T[] arr){
            this.arr = arr;
            length = arr.length;
            add_status = ADD_NIL;
        }

        public Vector(int length){
            arr = (T[])new Object[length];
            this.length = length;
            add_status = ADD_NIL;
        }

        public void add(Vector<? extends T> v){
            Vector<String> temp = new Vector<>(1);
            if (v.getLength() == length){
                T[] arr2 = v.getArr();

                for (int i = 0; i < length; i++){
                    if(arr2[i].getClass().isInstance(temp)){
                        // проверяем типы. Если это Vector, то:
                        ((Vector<T>)arr[i]).add(((Vector<T>)arr2[i]));
                    }
                    else { // иначе - это Number или String
                        add_v((Vector<T>) v);
                        add_status = ADD_OK;
                        break;
                    }
                }

                add_status = ADD_OK;
            }
            else{
                add_status = ADD_ERR;
            }
        }

        private void add_v(Vector<T> v){
            T[] arr2 = v.getArr();
            for(int i = 0; i < length; i++){
                arr[i] = (T) sum(arr[i], arr2[i]);
            }
        }

        public static Vector addVectors(Vector v1, Vector v2) throws CloneNotSupportedException {
            Vector ans = (Vector)v1.clone();

            ans.add(v2);

            return ans;
        }

        public int getLength(){
            return length;
        }

        public int get_add_status(){
            return add_status;
        }

        public T[] getArr(){
            return arr;
        }
    }

    public static void main(String[] args) {
    }
}
