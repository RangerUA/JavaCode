package ua.kopylov.temperature.printer;

import ua.kopylov.temperature.Temperature;

import java.util.List;

public interface Printer {
    void printJson(List<Temperature> list);
}
