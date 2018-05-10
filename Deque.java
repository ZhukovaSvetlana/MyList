public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
    T getFirst();
    T getLast();
    T pollFirst();
    T pollLast();

}
