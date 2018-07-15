package ua.kopylov.delivery.payer;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class MinimalPayer implements Payer {
    private int coinsCount;

    public MinimalPayer(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    @Override
    public int[] run(int[] fee) {
        checkNotNull(fee, "Fee must not be null!");
        int length = fee.length;
        checkArgument(coinsCount == length, String.format(
                    "Numbers of coins can't be less then numbers of checkpoints\n" +
                    "\t\tExpected numbers of checkpoints: %s, \n" +
                            "\t\tactual: %s", coinsCount, length));
        int[] pays = new int[length];

        for (int i = length; i > 0; i--) {
            for (int j = 0; j < length; j++) {
                if (fee[j] == i) {
                    pays[j] = coinsCount--;
                }
            }
        }

        return pays;
    }
}
