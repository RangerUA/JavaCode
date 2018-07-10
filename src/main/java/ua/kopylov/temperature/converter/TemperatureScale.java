package ua.kopylov.temperature.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public enum TemperatureScale {
    CELSIUS('C'), FAHRENHEIT('F'), KELVIN('K');
    private char abbreviation;
    private final Map<TemperatureScale, DoubleUnaryOperator> operator = new HashMap<>();

    TemperatureScale(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public char getAbbreviation() {
        return abbreviation;
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

    public static TemperatureScale valueOfAbbreviation(char source) {
        return Arrays.stream(values())
                .filter(target -> target.getAbbreviation() == source)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("TemperatureScale is wrong: " + source));
    }
}
