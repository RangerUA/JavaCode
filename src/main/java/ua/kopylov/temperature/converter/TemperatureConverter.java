package ua.kopylov.temperature.converter;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.extractor.Extractor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemperatureConverter implements Converter {
    private Extractor extractor;
    private String temperature;

    public TemperatureConverter(Extractor extractor, String temperature) {
        this.extractor = checkNotNull(extractor);
        this.temperature = checkNotNull(temperature, "Temperature must not be null!");
    }

    public List<Temperature> run() {
        Temperature data = extractor.get(temperature);
        TemperatureScale sourceScale = data.getScale();
        double degree = data.getDegree();
        checkArgument(TemperatureScale.isMinimalTemperature(sourceScale, degree),
                "Absolute zero - the minimum possible temperature is 0K (-459.67 ° F, -273.15 ° C).\n" +
                        "           Below this temperature value does not exist.\n" +
                        "           Actual: " + "\"" + temperature + "\"");

        return Stream.of(TemperatureScale.values())
                .filter(currentScale -> currentScale != sourceScale)
                .map(targetScale -> new Temperature(sourceScale.to(targetScale).applyAsDouble(degree), targetScale))
                .collect(Collectors.toList());
    }

}
