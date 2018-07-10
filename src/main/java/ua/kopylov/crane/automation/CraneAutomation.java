package ua.kopylov.crane.automation;

import java.util.ArrayList;
import java.util.List;

public class CraneAutomation {
    private List<PlateMovement> list = new ArrayList<PlateMovement>();

    public List<PlateMovement> run(PlateMovement movement) {
        if (movement.getPlateNumber() > 0) {
            run(new PlateMovement(
                    movement.getPlateNumber() - 1,
                    movement.getFromCar(),
                    movement.getPlace(),
                    movement.getToCar()));
            list.add(movement);
            run(new PlateMovement(
                    movement.getPlateNumber() - 1,
                    movement.getPlace(),
                    movement.getToCar(),
                    movement.getFromCar()));
        }
        return list;
    }
}
