package org.example.service;

import org.example.service.ChartingService;
import org.example.model.Charting;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChartingServiceTest {

    private final ChartingService chartingService = new ChartingService();

    @Test
    void testChartingService() {
        Charting data = chartingService.getChartData("w1");
        assertEquals("w1", data.widgetId());
        assertEquals(List.of("sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"), data.x());
        assertEquals(List.of(10, 35, 6, 47, 26, 55, 75), data.y());
        assertEquals(List.of("bad", "avg", "bad", "avg", "low", "avg", "good"), data.performance());
    }

}

