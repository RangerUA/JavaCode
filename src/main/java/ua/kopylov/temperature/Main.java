package ua.kopylov.temperature;

import ua.kopylov.temperature.converter.Converter;
import ua.kopylov.temperature.extractor.Extractor;
import ua.kopylov.temperature.printer.Printer;
import ua.kopylov.temperature.converter.TemperatureConverter;
import ua.kopylov.temperature.extractor.TemperatureExtractor;
import ua.kopylov.temperature.printer.ResultPrinter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Extractor extractor = new TemperatureExtractor();
        Converter converter = new TemperatureConverter(extractor, "0cccbcc");
        Printer printer = new ResultPrinter();
        List<Temperature> list = converter.run();
        printer.printJson(list);
    }
}
