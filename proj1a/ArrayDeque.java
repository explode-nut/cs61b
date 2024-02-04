public class ArrayDeque<T> {
    private int capacity = 8;
    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        this.size = 0;
        this.items = (T[]) new Object[capacity];
        this.first = capacity - 1;
        this.last = capacity - 1;
    }

    private void resizing() {
        T[] newItems = (T[]) new Object[capacity * 2];
        int i = first, j = capacity;
        for (; i != last; i = (i + 1) % capacity, j++) {
            newItems[j] = items[i];
        }
        newItems[j] = items[i];
        items = newItems;
        first = capacity;
        last = capacity * 2 - 1;
        capacity = capacity * 2;
    }

    public void addFirst(T item) {
        if (size() == 0) {
            items[first] = item;
            size += 1;
            return;
        }
        if (size() == capacity) {
            resizing();
        }
        if (first != 0) {
            first = first - 1;
        } else {
            first = capacity - 1;
        }
        items[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size() == 0) {
            items[last] = item;
            size += 1;
            return;
        }
        if (size() == capacity) {
            resizing();
        }
        last = (last + 1) % capacity;
        items[last] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int i = first;
        for (; i != last; i = (i + 1) % capacity) {
            System.out.println(items[i]);
        }
        System.out.println(items[i]);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = items[first];
        if (size != 1) {
            first = (first + 1) % capacity;
        }
        size -= 1;

        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = items[last];
        if (size != 1) {
            if (last == 0) {
                last = capacity - 1;
            } else {
                last -= 1;
            }
        }
        size -= 1;

        return res;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }
        return items[(first + index) % capacity];
    }
}
