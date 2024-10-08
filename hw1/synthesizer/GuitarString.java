//  Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.HashSet;
//import java.util.Iterator;
import java.util.Set;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //  Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<>((int) (SR / Math.round(frequency)));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //  Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        Set<Double> set = new HashSet<>();
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            while (set.contains(r)) {
                r = Math.random() - 0.5;
            }
            set.add(r);
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        //  Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        Double front = buffer.dequeue();
        Double res = ((front + buffer.peek()) / 2) * DECAY;
        buffer.enqueue(res);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        //  Return the correct thing.
        /*Iterator<Double> iterator = buffer.iterator();
        Double next = null;
        while (iterator.hasNext()) {
            next = iterator.next();
        }
        return next;*/
        return buffer.peek();
    }
}
