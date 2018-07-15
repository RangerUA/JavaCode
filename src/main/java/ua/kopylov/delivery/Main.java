package ua.kopylov.delivery;

import ua.kopylov.delivery.payer.MinimalPayer;
import ua.kopylov.delivery.payer.Payer;
import ua.kopylov.delivery.provider.FeeProvider;
import ua.kopylov.delivery.provider.RandomFeeProvider;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FeeProvider feeProvider = new RandomFeeProvider(
                10,
                55,
                1,
                10);
        Payer payer = new MinimalPayer(10);
        int[] fee = feeProvider.get();
        System.out.println(Arrays.toString(fee) + " -> fees");
        System.out.println(Arrays.toString(payer.run(fee)) + " -> payed");
    }
}
