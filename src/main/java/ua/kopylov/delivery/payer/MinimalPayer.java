package ua.kopylov.delivery.payer;

public class MinimalPayer implements Payer {
    private int coinsCount;

    public MinimalPayer(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    @Override
    public int[] run(int[] fee) {
        int length = fee.length;
        if (coinsCount < length) {
            throw new ArrayIndexOutOfBoundsException(String.format(
                    "Numbers of coins can't be less then numbers of checkpoints\n" +
                    "Expected numbers of checkpoints: %s, \n" +
                            "actual: %s", coinsCount, length));
        }
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
