package ua.kopylov.delivery.provider;

import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class RandomFeeProvider implements FeeProvider {
    private final int checkpoint;
    private final int min;
    private final int max;
    private final int minimalCoinsSum;

    public RandomFeeProvider(final int checkpointsCount,
                             final int minimalCoinsSum,
                             final int randomMin,
                             final int randomMax) {
        this.checkpoint = checkpointsCount;
        this.min = randomMin;
        this.max = randomMax;
        this.minimalCoinsSum = minimalCoinsSum;
    }

    private int getRandom() {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    public int[] get() {
        checkArgument(checkpoint == 10,
                "Number of checkpoint should be equals 10");
        int fees[] = new int[checkpoint];
        for(int i = 0; i < checkpoint; i++){
            fees[i] = getRandom();
        }
        int sum = IntStream.of(fees).sum();
        if (sum > minimalCoinsSum) {
            return fees;
        }
        return get();
    }

}
