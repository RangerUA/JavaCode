package ua.kopylov.temperature.extractor;

import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.converter.TemperatureScale;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class TemperatureExtractor implements Extractor {

    public Temperature get(String temperature) {
        checkNotNull(temperature, "Temperature must not be null!");
        checkArgument(temperature.length() > 1, "Temperature length should be > 1");

        temperature = temperature.toUpperCase();
        TemperatureScale scale = TemperatureScale.valueOfAbbreviation(temperature
                .charAt(temperature.length() - 1));
        String sourceDegree = temperature
                .replace(String.valueOf(scale.getAbbreviation()), "");
        checkArgument(TemperatureScale.isInteger(sourceDegree),
                "Invalid temperature value: " + "\"" + sourceDegree + "\"");
        double degree = Integer.parseInt(sourceDegree);
        return new Temperature(degree, scale);
    }

}
