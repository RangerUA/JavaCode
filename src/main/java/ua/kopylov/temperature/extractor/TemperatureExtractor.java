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
        checkArgument(temperature.length() > 1,"Temperature length should be > 1");

        temperature = temperature.toUpperCase();
        TemperatureScale scale = TemperatureScale.valueOfAbbreviation(temperature
                .charAt(temperature.length() - 1));
        double degree = Integer.parseInt(temperature
                .replace(String.valueOf(scale.getAbbreviation()), ""));
        checkArgument(TemperatureScale.isMinimalTemperature(scale, degree),
                "Absolute zero - the minimum possible temperature is 0K (-459.67 ° F, -273.15 ° C).\n" +
                        "           Below this temperature value does not exist.\n" +
                        "           Actual: " + temperature);
        return new Temperature(degree, scale);
    }

}
