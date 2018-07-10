package ua.kopylov.temperature.extractor;

import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.converter.TemperatureScale;

import java.util.stream.Stream;

public class TemperatureExtractor implements Extractor{

    public Temperature get(String temperature) {
        if (temperature == null || temperature.equals("")) {
            throw new NullPointerException("Temperature can't be empty");
        }
        if (temperature.length() < 2) {
            throw new NumberFormatException("Wrong temperature format: " + temperature);
        }

        temperature = temperature.toUpperCase();
        TemperatureScale scale = TemperatureScale.valueOfAbbreviation(temperature
                .charAt(temperature.length() - 1));
        double degree = Integer.parseInt(temperature
                .replace(String.valueOf(scale.getAbbreviation()), ""));
        return new Temperature(degree, scale);
    }

}
