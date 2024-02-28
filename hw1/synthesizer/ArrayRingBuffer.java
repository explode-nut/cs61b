// Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        //  Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        //  Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        //  Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class MyIterator<K> implements Iterator<K> {
        int count = 0;
        int nextItemIndex = first;

        @Override
        public boolean hasNext() {
            if (count != fillCount()) {
                count += 1;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public K next() {
            K nextItem = (K) rb[nextItemIndex];
            nextItemIndex += 1;
            nextItemIndex = nextItemIndex % capacity();
            return nextItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    //  When you get to part 5, implement the needed code to support iteration.
}
