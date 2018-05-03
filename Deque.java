public interface Deque {
    void addFirst(Object item);
    void addLast(Object item);
    Object removeFirst();
    Object removeLast();
    Object getFirst();
    Object getLast();
    Object pollFirst();
    Object pollLast();

}
