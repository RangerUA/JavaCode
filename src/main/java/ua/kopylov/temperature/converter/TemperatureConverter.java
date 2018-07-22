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

    public TemperatureConverter(Extractor extractor) {
        this.extractor = checkNotNull(extractor);
    }

    public List<Temperature> run(String temperature) {
        checkNotNull(temperature, "Temperature must not be null!");
        Temperature data = extractor.get(temperature);
        TemperatureScale sourceScale = data.getScale();
        double degree = data.getDegree();
        checkArgument(sourceScale.getMinimum() <= degree,
                "Minimum temperature should be: 0°K, -459.67°F, -273.15°C\n" +
                        "\t\t\tActual: " + "\"" + temperature + "\"");

        return Stream.of(TemperatureScale.values())
                .filter(currentScale -> currentScale != sourceScale)
                .map(targetScale -> new Temperature(sourceScale.to(targetScale).applyAsDouble(degree), targetScale))
                .collect(Collectors.toList());
    }

}
