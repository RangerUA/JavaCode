package ua.kopylov.temperature.converter;

import ua.kopylov.temperature.Temperature;

import java.util.List;

public interface Converter {
    List<Temperature> run(String temperature);
}
