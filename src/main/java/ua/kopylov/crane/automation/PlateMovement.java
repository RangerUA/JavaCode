package ua.kopylov.crane.automation;

public final class PlateMovement {
    private int plateNumber;
    private final String fromCar;
    private final String toCar;
    private final String place;

    public PlateMovement(
            int plateNumber,
            String fromCar,
            String toCar,
            String place) {
        this.plateNumber = plateNumber;
        this.fromCar = fromCar;
        this.toCar = toCar;
        this.place = place;
    }

    int getPlateNumber() {
        return plateNumber;
    }

    String getFromCar() {
        return fromCar;
    }

    String getToCar() {
        return toCar;
    }

    String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "#" + plateNumber + " " + fromCar + " -> " + toCar.trim() + "\n";
    }
}
