import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int NOT_FOUND = -1;
    private T[] array;
    private int size = 0;

    private ArrayList(T[] array) {
        this.array = array;
        size = array.length - 1;
    }

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (T[]) new Object[10];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
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
    public void add(T item) {
        growAsNeeded();
        array[size++] = item;
    }

    private void growAsNeeded() {
        if (array.length == size) {
           /* T[] newArray = new T[(array.length * 3 / 2) + 1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray; */
            array = Arrays.copyOf(array, (array.length * 3 / 2) + 1);
        }
    }

    @Override
    public void add(int index, T item) {
        checkForRange(index);
        growAsNeeded();
        shiftItemsToRight(index);
        array[index] = item;
        size++;
    }

    private void checkForRange(int index) {
        if ((index > size) || (index < 0))
            throw new IndexOutOfBoundsException();
    }

    private void shiftItemsToRight(int index) {
        for (int i = size; i > index; --i) {
            array[i] = array[i - 1];
        }
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(T obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int lastIndexOf(T obj) {
        for (int i = size - 1; i >= 0; i--) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void set(int index, T item) {
        checkForRange(index);
        array[index] = item;
    }

    @Override
    public void remove(int index) {
        checkForRange(index);
        shiftItemsToLeft(index);
        array[--size] = null;
    }

    private void shiftItemsToLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == NOT_FOUND)
            return false;

        remove(index);
        return true;
    }

    @Override
    public List<T> subList(int from, int to) {
        List<T> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add(array[i]);
        }
        return list;
    }
}
