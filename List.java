public interface List<T> {
    boolean isEmpty();
    int size();
    void add(T item);
    void add(int index, T item);
    T get(int index);
    int indexOf(T obj);
    int lastIndexOf(T obj);
    void set(int index, T item);
    void remove(int index);
    boolean remove(T item);
    List subList(int from, int to);
}
