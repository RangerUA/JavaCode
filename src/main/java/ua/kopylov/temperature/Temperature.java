package ua.kopylov.temperature;

import ua.kopylov.temperature.converter.TemperatureScale;

public class Temperature {
    private TemperatureScale scale;
    private double degree;

    public Temperature(double degree, TemperatureScale scale) {
        this.degree = degree;
        this.scale = scale;
    }

    public TemperatureScale getScale() {
        return scale;
    }

    public double getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        return  scale +
                ": \"" + degree + "\"";
    }
}
