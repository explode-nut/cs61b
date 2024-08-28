package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state);
    }

    private double normalize(int state) {
        int normalizedState = (state % period + period) % period; // 确保在 [0, period-1] 范围内
        return (normalizedState - period / 2.0) / (period / 2.0);
    }
}
