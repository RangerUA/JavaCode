package ua.kopylov.temperature.converter;

import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.extractor.Extractor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemperatureConverter implements Converter {
    private Extractor extractor;
    private String temperature;

    public TemperatureConverter(Extractor extractor, String temperature) {
        this.extractor = extractor;
        this.temperature = temperature;
    }

    public List<Temperature> run() {
        Temperature data = extractor.get(temperature);
        TemperatureScale sourceScale = data.getScale();
        double degree = data.getDegree();

        return Stream.of(TemperatureScale.values())
                .filter(currentScale -> currentScale != sourceScale)
                .map(targetScale -> new Temperature(sourceScale.to(targetScale).applyAsDouble(degree), targetScale))
                .collect(Collectors.toList());
    }

}
