import org.omg.CORBA.Object;

import java.util.Arrays;

public class ArrayList implements List {
    private static final int NOT_FOUND = -1;
    private Object[] array;
    private int size = 0;

    public ArrayList() {
        array = new Object[10];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object item) {
        growAsNeeded();
        array[size++] = item;
    }

    private void growAsNeeded() {
        if (array.length == size) {
           /* Object[] newArray = new Object[(array.length * 3 / 2) + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray; */
            array = Arrays.copyOf(array, (array.length * 3 / 2) + 1);
        }
    }

    @Override
    public void add(int index, Object item) {
        checkForRange(index);
        growAsNeeded();
        shiftItems(index);
        array[index] = item;
        size++;
    }

    private void checkForRange(int index) {
        if ((index > size) || (index < 0))
            throw new IndexOutOfBoundsException();
    }

    private void shiftItems(int index) {
        for (int i = size; i > index; --i) {
            array[i] = array[i - 1];
        }
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int lastIndexOf(Object obj) {

        for (int i = size - 1; i >= 0; i--) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void set(int index, Object item) {
        array[index] = item;
    }

    @Override
    public void remove(int index) {
        for(int i = index; i < size; i-- ) {
            array[i] = array[i+1];
        }
    }

    @Override
    public void remove(Object item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                for (int j = i; j < size; j++) {
                    array[j] = array[j + 1];
                }
            }
            break;
        }
    }

    @Override
    public List subList(int from, int to) {

        return null;
    }
}
