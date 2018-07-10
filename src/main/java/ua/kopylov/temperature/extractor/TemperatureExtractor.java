package ua.kopylov.temperature.extractor;

import com.google.common.base.Preconditions;
import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.converter.TemperatureScale;

import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class TemperatureExtractor implements Extractor{

    public Temperature get(String temperature) {
        checkNotNull(temperature, "Temperature must not be null!");
        checkArgument(temperature.length() < 2,
                "Wrong temperature format: " + temperature);

        temperature = temperature.toUpperCase();
        TemperatureScale scale = TemperatureScale.valueOfAbbreviation(temperature
                .charAt(temperature.length() - 1));
        double degree = Integer.parseInt(temperature
                .replace(String.valueOf(scale.getAbbreviation()), ""));
        return new Temperature(degree, scale);
    }

}
