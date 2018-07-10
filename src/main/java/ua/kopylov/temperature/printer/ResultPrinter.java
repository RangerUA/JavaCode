package ua.kopylov.temperature.printer;

import ua.kopylov.temperature.Temperature;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPrinter implements Printer {

    public void printJson(List<Temperature> list) {
        System.out.println(list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "{", "}")));
    }

}
