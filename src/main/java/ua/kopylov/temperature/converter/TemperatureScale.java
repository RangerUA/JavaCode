package ua.kopylov.temperature.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public enum TemperatureScale {
    CELSIUS('C', -273.15d), FAHRENHEIT('F', -459.67d), KELVIN('K', 0d);
    private char abbreviation;
    private double minimum;
    private final Map<TemperatureScale, DoubleUnaryOperator> operator = new HashMap<>();

    TemperatureScale(char abbreviation, double minimum) {
        this.abbreviation = abbreviation;
        this.minimum = minimum;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public double getMinimum() {
        return minimum;
    }

    public DoubleUnaryOperator to(TemperatureScale to) {
        return to == this
                ? DoubleUnaryOperator.identity() : operator.get(to);
    }

    static {
        put(CELSIUS, FAHRENHEIT, c -> c * 1.8 + 32.0);
        put(CELSIUS, KELVIN, c -> c + 273.15);
        put(FAHRENHEIT, CELSIUS, f -> (f - 32.0) / 1.8);
        put(FAHRENHEIT, KELVIN, f -> (f + 459.67) / 1.8);
        put(KELVIN, FAHRENHEIT, k -> k * 1.8 - 459.67);
        put(KELVIN, CELSIUS, k -> k - 273.15);
    }

    @Override
    public String toString() {
        return "\"" + abbreviation + "\"";
    }

    private static void put(TemperatureScale from, TemperatureScale to, DoubleUnaryOperator op) {
        from.operator.put(to, op);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static TemperatureScale valueOfAbbreviation(char source) {
        return Arrays.stream(values())
                .filter(target -> target.getAbbreviation() == source)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("TemperatureScale is wrong: " + "\"" + source + "\""));
    }
}
