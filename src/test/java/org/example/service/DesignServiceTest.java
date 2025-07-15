package org.example.service;

import org.example.model.ChartingDesign;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesignServiceTest {

    private final DesignService designService = new DesignService();

    @Test
    void testDesignService() {
        ChartingDesign designData = designService.getDesign("w1");


        assertEquals("w1", designData.widgetId());
//        assertEquals("line", designData.chartType());
        assertEquals("Day", designData.xAxisField());
        assertEquals("Events", designData.yAxisField());
        assertEquals("performance", designData.performanceField());
    }
}
