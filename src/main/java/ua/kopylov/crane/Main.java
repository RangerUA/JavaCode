package ua.kopylov.crane;

import ua.kopylov.crane.automation.CraneAutomation;
import ua.kopylov.crane.automation.PlateMovement;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CraneAutomation automation = new CraneAutomation();
        PlateMovement movement = new PlateMovement(
                4,
                "slot_a",
                "slot_c",
                "slot_b");
        String list = Arrays.toString(automation.run(movement).toArray())
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "");
        System.out.println(list);
    }
}
