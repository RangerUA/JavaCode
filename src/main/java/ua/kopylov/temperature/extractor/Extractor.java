package ua.kopylov.temperature.extractor;

import ua.kopylov.temperature.Temperature;

public interface Extractor {
    Temperature get(String temperature);
}
