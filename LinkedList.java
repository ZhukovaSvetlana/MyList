import java.util.NoSuchElementException;

public class LinkedList implements Deque, List {
    private static final int NOT_FOUND = -1;

    private Node first;
    private Node last;
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
    public void add(Object item) {
        addLast(item);
    }

    @Override
    public void add(int index, Object item) {
        Node current = (index < size / 2) ? getNodeFromLeft(index, item) : getNodeFromRight(index, item);
        Node added = new Node(item, current, (current != null) ? current.prev : null);
        if (added.next != null)
            added.next.prev = added;
        if (added.prev != null)
            added.prev.next = added;
        ++size;
    }

    private Node getNodeFromLeft(int index, Object item) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node getNodeFromRight(int index, Object item) {
        Node current = last;
        for (int i = size - 1; i > index; i++) {
            current = current.prev;
        }
        return current;
    }

    @Override
    public Object get(int index) {
        for (int i = 0; i <= index; i++) {
            first = first.next;
        }
        return first.item;
    }

    @Override
    public int indexOf(Object obj) {
        int index = NOT_FOUND;

        if (obj.equals(first.item))
            index = 0;

        while (!obj.equals(first.item) && (index < size)) {
            first = first.next;
            index++;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object obj) {
        int index = NOT_FOUND;

        if (obj.equals(last.item))
            index = size - 1;

        while (!obj.equals(last.item) && (index > 0)) {
            last = last.prev;
            index--;
        }
        return index;
    }

    @Override
    public void set(int index, Object item) {
        for (int i = 0; i <= index; i++) {
            first = first.next;
        }
        first.item = item;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public boolean remove(Object item) {
        return false;
    }

    @Override
    public List subList(int from, int to) {
        return null;
    }

    private static class Node {
        private Object item;
        private Node next;
        private Node prev;

        private Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void addFirst(Object item) {
        if (first != null) {
            first = new Node(item, first, null);
            first.next.prev = first;
        } else {
            first = last = new Node(item, null, null);
        }
        ++size;
    }

    @Override
    public void addLast(Object item) {
        if (last != null) {
            last = new Node(item, null, last);
            last.prev.next = last;
        } else {
            first = last = new Node(item, null, null);
        }
        ++size;
    }

    @Override
    public Object removeFirst() {
        checkForRange();
        return deleteFirstNode();
    }

    private Object deleteFirstNode() {
        Object deletedItem = first.item;

        first = first.next;
        first.prev.next = null;
        first.prev = null;
        --size;

        return deletedItem;
    }

    private void checkForRange() {
        if (size <= 0)
            throw new NoSuchElementException();
    }

    @Override
    public Object removeLast() {
        checkForRange();
        return deleteLastNode();
    }

    private Object deleteLastNode() {
        Object deletedItem = last.item;

        last = last.prev;
        last.next.prev = null;
        last.next = null;
        --size;

        return deletedItem;
    }

    @Override
    public Object getFirst() {
        checkForRange();
        return first.item;
    }

    @Override
    public Object getLast() {
        checkForRange();
        return last.item;
    }

    @Override
    public Object pollFirst() {
        return (size == 0) ? null : deleteFirstNode();
    }

    @Override
    public Object pollLast() {
        return (size == 0) ? null : deleteLastNode();
    }
}
