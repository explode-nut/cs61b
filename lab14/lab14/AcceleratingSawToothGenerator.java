package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double speed;
    private int state;

    public AcceleratingSawToothGenerator(int period, double speed) {
        this.state = 0;
        this.period = period;
        this.speed = speed;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state);
    }

    private double normalize(int state) {
        int normalizedState = (state % period + period) % period;
        return (normalizedState - period / 2.0) / (period / 2.0);
    }
}
