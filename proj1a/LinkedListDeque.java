public class LinkedListDeque<T> {
    private int size;
    private class DequeNode {
        T item;
        DequeNode next;
        DequeNode front;
        public DequeNode(T item) {
            this.item = item;
            this.next = null;
            this.front = null;
        }
    }
    private DequeNode first;
    private DequeNode last;

    public LinkedListDeque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public void addFirst(T item) {
        DequeNode node = new DequeNode(item);
        if (size() == 0) {
            first = node;
            last = node;
            size += 1;
            return;
        }
        first.front = node;
        node.next = first;
        first = node;
        size += 1;
    }

    public void addLast(T item) {
        DequeNode node = new DequeNode(item);
        if (size() == 0) {
            first = node;
            last = node;
            size += 1;
            return;
        }
        last.next = node;
        node.front = last;
        last = node;
        size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        DequeNode tmp = first;
        while (tmp != null) {
            System.out.println(tmp.item + " ");
            tmp = tmp.next;
        }
    }

    public T removeFirst() {
        if (first == null) {
            return null;
        }
        T res = first.item;
        first = first.next;
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (last == null) {
            return null;
        }
        T res = last.item;
        last = last.front;
        size -= 1;
        return res;
    }

    public T get(int index) {
        DequeNode tmp = first;
        for (int i = 0; i != index; i++) {
            tmp = tmp.next;
        }

        return tmp.item;
    }

    public T getRecursive(int index) {
        DequeNode tmp = first;
        return recursive(index, tmp);
    }

    private T recursive(int index, DequeNode tmp) {
        if (index == 0) {
            return tmp.item;
        }
        return recursive(index-1, tmp.next);
    }

}
