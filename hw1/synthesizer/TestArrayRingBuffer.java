package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        boolean passed = arb.isEmpty();
        assertTrue("1.expected true but got false", passed);
        System.out.println("1.test1 passed");
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(2);
        passed = arb.isFull() && passed;
        assertTrue("2.expected true but got false", passed);
        System.out.println("2.test2 passed");
        try {
            arb.enqueue(1);
        } catch (Exception e) {
            System.out.println("3.test3 passed");
        }
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        passed = arb.isEmpty() && passed;
        assertTrue("4.expected true but got false", passed);
        System.out.println("4.test4 passed");

        System.out.println("when enqueue 1,3,2 here are the iteration");
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(2);
        Iterator<Integer> iterator = arb.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
