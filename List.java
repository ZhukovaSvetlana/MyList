public interface List {
    boolean isEmpty();
    int size();
    void add(Object item);
    void add(int index, Object item);
    Object get(int index);
    int indexOf(Object obj);
    int lastIndexOf(Object obj);
    void set(int index, Object item);
    void remove(int index);
    boolean remove(Object item);
    List subList(int from, int to);
}
