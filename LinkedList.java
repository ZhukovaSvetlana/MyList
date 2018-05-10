import java.util.NoSuchElementException;

public class LinkedList<T> implements Deque<T>, List<T> {
    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        private Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    
    private static final int NOT_FOUND = -1;

    private Node<T> first;
    private Node<T> last;
    private int size;

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
        addLast(item);
    }

    @Override
    public void add(int index, T item) {
        Node<T> current = getNode(index);
        Node<T> added = new Node<>(item, current, (current != null) ? current.prev : null);
        if (added.next != null)
            added.next.prev = added;
        if (added.prev != null)
            added.prev.next = added;
        ++size;
    }

    private Node<T> getNode(int index) {
        return (index < size / 2) ? getNodeFromLeft(index) : getNodeFromRight(index);
    }

    private Node<T> getNodeFromLeft(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node<T> getNodeFromRight(int index) {
        Node<T> current = last;
        for (int i = size - 1; i > index; i++) {
            current = current.prev;
        }
        return current;
    }

    @Override
    public T get(int index) {
        checkForRange(index);
        return getNode(index).item;
    }

    @Override
    public int indexOf(T obj) {
        int index = 0;
        for (Node<T> current = first; (index < size) && !obj.equals(current.item); index++) {
            current = current.next;
        }
        return (index == size) ? NOT_FOUND : index;
    }

    @Override
    public int lastIndexOf(T obj) {
        int index = size - 1;
        for (Node<T> current = last; (index >= 0) && !obj.equals(current.item); index--) {
            current = current.prev;
        }
        return index;
    }

    @Override
    public void set(int index, T item) {
        checkForRange(index);
        getNode(index).item = item;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public boolean remove(T item) {
        return false;
    }

    @Override
    public List<T> subList(int from, int to) {
        return null;
    }

    @Override
    public void addFirst(T item) {
        if (first != null) {
            first = new Node<>(item, first, null);
            first.next.prev = first;
        } else {
            first = last = new Node<>(item, null, null);
        }
        ++size;
    }

    @Override
    public void addLast(T item) {
        if (last != null) {
            last = new Node<>(item, null, last);
            last.prev.next = last;
        } else {
            first = last = new Node<>(item, null, null);
        }
        ++size;
    }

    @Override
    public T removeFirst() {
        checkForRange();
        return deleteFirstNode();
    }

    private T deleteFirstNode() {
        T deletedItem = first.item;

        first = first.next;
        first.prev.next = null;
        first.prev = null;
        --size;

        return deletedItem;
    }

    private void checkForRange() {
        checkForRange(0);
    }

    private void checkForRange(int index) {
        if (size <= index) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public T removeLast() {
        checkForRange();
        return deleteLastNode();
    }

    private T deleteLastNode() {
        T deletedItem = last.item;

        last = last.prev;
        last.next.prev = null;
        last.next = null;
        --size;

        return deletedItem;
    }

    @Override
    public T getFirst() {
        checkForRange();
        return first.item;
    }

    @Override
    public T getLast() {
        checkForRange();
        return last.item;
    }

    @Override
    public T pollFirst() {
        return (size == 0) ? null : deleteFirstNode();
    }

    @Override
    public T pollLast() {
        return (size == 0) ? null : deleteLastNode();
    }
}
