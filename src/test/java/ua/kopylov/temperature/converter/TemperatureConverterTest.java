package ua.kopylov.temperature.converter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import ua.kopylov.temperature.Temperature;
import ua.kopylov.temperature.extractor.Extractor;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureConverterTest {
    @InjectMocks
    private TemperatureConverter converter;

    @Mock
    private Extractor extractor;

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void convertAbsoluteZero() {
        String temperature = "0K";
        when(extractor.get(temperature)).thenReturn(new Temperature(0d, TemperatureScale.KELVIN));

        List<Temperature> actual = converter.run(temperature);

        assertNotNull(actual);
        assertEquals(TemperatureScale.values().length - 1, actual.size());
        assertEquals(TemperatureScale.CELSIUS, actual.get(0).getScale());
        assertEquals(-273.15d, actual.get(0).getDegree(), 0.01d);
        assertEquals(TemperatureScale.FAHRENHEIT, actual.get(1).getScale());
        assertEquals(-459.67d, actual.get(1).getDegree(), 0.01d);
    }

    public void convertNull() {

    }
}